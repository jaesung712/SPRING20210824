package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class PartnerRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create(){
        Partner partner = Partner.builder()
                .name("베스트샵")
                .status("사용중")
                .address("서울시 금천구")
                .callCenter("070-4444-4444")
                .businessNumber("444-44-444444")
                .ceoName("오지환")
                .regDate(LocalDateTime.now())
                .categoryId(1L)
                .build();
        Partner newPartner = partnerRepository.save(partner);
    }
    @Test
    public void update(){
        Optional<Partner> partner = partnerRepository.findById(1L);
        partner.ifPresent(selectcategory ->{
            selectcategory.setName("삼성전자");
            selectcategory.setStatus("주가폭등");
            selectcategory.setCeoName("이메론");
            partnerRepository.save(selectcategory);
        });
    }
    @Test
    public void read(){
        Optional<Partner> partner= partnerRepository.findFirstByNameOrderByIdDesc("현대자동차");
        partner.ifPresent(selectpartner ->{
            System.out.println(selectpartner.getName());
            System.out.println(selectpartner.getStatus());
            System.out.println(selectpartner.getAddress());
        });
    }


    @Test
    public void delete() {
        Optional<Partner> partner = partnerRepository.findById(21L);
        partner.ifPresent(selectUser -> {
            partnerRepository.delete(selectUser);
        });
        Optional<Partner> delpartner = partnerRepository.findById(21L);
        if (delpartner.isPresent()) {
            System.out.println("삭제실패!");
        }else {
            System.out.println("삭제성공!");
        }
    }


}
