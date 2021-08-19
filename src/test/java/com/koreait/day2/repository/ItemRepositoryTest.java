package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = Item.builder()
                .name("엘지 냉장고")
                .status("판매중")
                .title("양문형 냉장고")
                .content("아주 시원해요")
                .price(BigDecimal.valueOf(2000000))
                .regDate(LocalDateTime.now())
                .partnerId(4L)
                .build();
        Item newItem = itemRepository.save(item);
    }
    @Test
    public void update(){
        Optional<Item> item = itemRepository.findById(3L);
        item.ifPresent(selectcategory ->{
            selectcategory.setTitle("양문형 냉장고");
            selectcategory.setUpdateDate(LocalDateTime.now());
            itemRepository.save(selectcategory);
        });
    }
    @Test
    public void read(){
        Optional<Item> item = itemRepository.findFirstByNameOrderByIdDesc("엘지 냉장고");
        item.ifPresent(selectitem ->{
            System.out.println("제품 : " + selectitem.getName());
        });
    }

    @Test
    public void delete() {
        Optional<Item> item = itemRepository.findById(5L);
        item.ifPresent(selectUser -> {
            itemRepository.delete(selectUser);
        });
        Optional<Item> delitem = itemRepository.findById(5L);
        if (delitem.isPresent()) {
            System.out.println("삭제실패!");
        }else {
            System.out.println("삭제성공!");
        }
    }
    //

}
