package jpabook.jpashop.domain.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
//상속관계 Mapping
@DiscriminatorValue("A")
public class Album extends Item{
    private String artist;
    private String etc;
}