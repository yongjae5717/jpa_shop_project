package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
