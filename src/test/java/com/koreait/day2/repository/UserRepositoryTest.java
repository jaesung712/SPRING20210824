package com.koreait.day2.repository;

import com.koreait.day2.Day2ApplicationTests;
import com.koreait.day2.model.entity.Users;
import com.koreait.day2.model.enumclass.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends Day2ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
//        Users user = new Users();
//        user.setUserid("banana");
//        user.setUserpw("1234");
//        user.setHp("010-2222-2222");
//        user.setEmail("banana@banana.com");
//        user.setRegDate(LocalDateTime.now());
//
//        Users newUser = userRepository.save(user);

        Users user = Users.builder()
                .userid("orange")
                .userpw("5555")
                .hp("010-5555-5555")
                .email("orange@orange.com")
                .status(UserStatus.REGISTERD)
                .regDate(LocalDateTime.now())
                .build();
        Users newUser = userRepository.save(user);
    }

    @Test
    @Transactional
    public void read(){
        // select * from users where userid=?
//        Optional<Users> user = userRepository.findByUserid("banana");
//        user.ifPresent(selectUser -> {
//            System.out.println("users : " + selectUser);
//            System.out.println("userid : " + selectUser.getUserid());
//            System.out.println("userpw : " + selectUser.getUserpw());
//            System.out.println("hp : " + selectUser.getHp());
//            System.out.println("email : " + selectUser.getEmail());
//        });

//        Users user = userRepository.findFirstByHpOrderByIdDesc("010-2222-1111");
//        if(user != null){
//            System.out.println("데이터가 존재합니다!");
//        }else{
//            System.out.println("데이터가 존재하지 않습니다!");
//        }

        Users user = userRepository.findFirstByHpOrderByIdDesc("010-3333-3333");
        if(user != null){
            System.out.println(user.getUserid() + "님의 주문 리스트입니다");
            System.out.println("-------------------------------------");
            // System.out.println(user.getOrderGroupList().stream().count());
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("****** 주문내역 ******");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("수량 : " + orderGroup.getTotalQuantity());
                System.out.println("금액 : " + orderGroup.getTotalPrice());
                System.out.println("****** 주문상세 ******");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("주문의 상태 : " + orderDetail.getStatus());
                    System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());
                    System.out.println("상품명 : " + orderDetail.getItem().getName());
                    // 파트너사명, 고객센터전화번호, 상품카테고리
                    System.out.println("파트너명 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("고객센터 전화번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("상품카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println();
                });
            });

        }else{
            System.out.println("데이터가 존재하지 않습니다");
        }

    }

    @Test
    public void update(){
        Optional<Users> user = userRepository.findByUserid("banana");
        user.ifPresent(selectUser -> {
            selectUser.setEmail("banana@naver.com");
            selectUser.setHp("010-0000-0000");
            selectUser.setUpdateDate(LocalDateTime.now());
            userRepository.save(selectUser);
        });
    }

    @Test
    public void delete(){
        Optional<Users> user = userRepository.findByUserid("banana");

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<Users> deleteUser = userRepository.findByUserid("banana");
        if(deleteUser.isPresent()){
            System.out.println("삭제실패!");
        }else{
            System.out.println("삭제성공!");
        }
    }
}
