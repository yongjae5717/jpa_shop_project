package jpabook.jpashop.domain;

import jpabook.jpashop.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    // 관계형 DB는 Collection관계를 양쪽에 가질 수 없기 때문에
    // 관계를 풀어낼 수 있도록 중간 테이블을 설정을 해주어야한다.
    // 실전에서 다대다는 사용할 수 없을 확률이 크다.
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "categoiy_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();


    //같은 Entity에 대해서 부모-자식 관계 설정하기
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}