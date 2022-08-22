package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    //FK (연관관계의 주인)
    @JoinColumn(name = "member_id")
    private Member member;

    //CascadeType.All 으로 인하여 각각 persist를해주는 것을 한줄로 간결하게 구현가능하다.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // order를 저장할 때, delivery도 persist를 같이해준다.
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //FK (연관관계의 주인)
    @JoinColumn(name = "delevery_id")
    private Delivery delivery;

    // hibernate 지원해줌 (주문시간)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 (ORDER, CANCEL)

    //==연관관계 편의 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
