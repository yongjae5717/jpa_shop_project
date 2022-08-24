package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item.Book;
import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional //쓰기모드
    // 아이템 저장하기
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    //변경감지(dirty checking)
    @Transactional
    public void updateItem(Long itemId, String name, int price, int quantity){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(quantity);
    }
    //전체 아이템 찾기
    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    //한가지 아이템 찾기
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}