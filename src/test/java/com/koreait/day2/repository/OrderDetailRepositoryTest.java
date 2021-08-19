package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.OrderDetail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDetailRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDetail orderDetail = OrderDetail.builder()
                .status("결제완료")
                .quantity(1)
                .totalPrice(BigDecimal.valueOf(1500000))
                .regDate(LocalDateTime.now())
                .itemId(1L)
                .orderGroupId(1L)
                .build();
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

    }


    @Test
    public void read() {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findFirstByIdOrderByIdDesc(5L);
        orderDetail.ifPresent(selectOD -> {
            System.out.println(selectOD.getId());
            System.out.println(selectOD.getStatus());
            System.out.println(selectOD.getTotalPrice());
            System.out.println(selectOD.getRegDate());
        });
    }

    @Test
    public void delete() {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(1L);
        orderDetail.ifPresent(selectUser -> {
            orderDetailRepository.delete(selectUser);
        });
        Optional<OrderDetail> delitem = orderDetailRepository.findById(1L);
        if (delitem.isPresent()) {
            System.out.println("삭제실패!");
        } else {
            System.out.println("삭제성공!");
        }
    }

    @Test
    public void update() {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(10L);
        orderDetail.ifPresent(selectordetail -> { //selectUser 임의로 지음
            selectordetail.setStatus("배송중");
            selectordetail.setItemId(3L);
            selectordetail.setRegDate(LocalDateTime.now());
            orderDetailRepository.save(selectordetail); //유저 레포지토리에 저장
        });


    }
}
