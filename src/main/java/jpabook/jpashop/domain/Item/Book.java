package jpabook.jpashop.domain.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
//상속관계 Mapping
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
    private String isbn;
}
