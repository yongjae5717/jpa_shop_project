package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    // 아이템 저장
    public void save(Item item){
        if(item.getId() == null){
            em.persist(item);
        } else{
            em.merge(item); //update와 비슷하다.
        }
    }

    // 아이템 1개 조회
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    // 전체 아이템 조회
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}