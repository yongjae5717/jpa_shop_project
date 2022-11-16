package jpabook.jpashop.domain.entity.item;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@Getter
@NoArgsConstructor
public class Album extends Item {

    private String artist;
    private String etc;
}
