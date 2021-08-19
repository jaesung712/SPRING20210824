package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    // 자동차2, 컴퓨터2, 가전2
    @Test
    public void create(){
        Category category = Category.builder()
                .type("컴퓨터")
                .title("애플 컴퓨터")
                .regDate(LocalDateTime.now())
                .build();

        Category newCategory = categoryRepository.save(category);
    }

    @Test
    public void update(){
        Optional<Category> category = categoryRepository.findById(1L);
        category.ifPresent(selectcategory ->{
            selectcategory.setType("컴퓨터");
            selectcategory.setTitle("애플 컴퓨터");
            selectcategory.setUpdateDate(LocalDateTime.now());
            categoryRepository.save(selectcategory);
        });
    }
    @Test
    public void read(){
        Optional<Category> category= categoryRepository.findFirstByTitleOrderByIdDesc("애플 컴퓨터");
        category.ifPresent(selectcategory ->{
            System.out.println("컴퓨터 : " + selectcategory.getTitle());
        });
    }

    @Test
    public void delete() {
        Optional<Category> category = categoryRepository.findById(5L);
        category.ifPresent(selectUser -> {
            categoryRepository.delete(selectUser);
        });
        Optional<Category> delcategory = categoryRepository.findById(5L);
        if (delcategory.isPresent()) {
            System.out.println("삭제실패!");
        }else {
            System.out.println("삭제성공!");
        }
    }

}
