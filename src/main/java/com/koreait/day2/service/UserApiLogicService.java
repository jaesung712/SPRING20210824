package com.koreait.day2.service;

import com.koreait.day2.ifs.CrudInterface;
import com.koreait.day2.model.entity.Users;
import com.koreait.day2.model.enumclass.UserStatus;
import com.koreait.day2.model.network.Header;
import com.koreait.day2.model.network.request.UserApiRequest;
import com.koreait.day2.model.network.response.UserApiResponse;
import com.koreait.day2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //서비스로직어노테이션-> 서비스 레이어 , 내부에서 자바 로직을 처리함
@RequiredArgsConstructor
public class UserApiLogicService implements CrudInterface<UserApiRequest , UserApiResponse> {

    private final UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        UserApiRequest userApiRequest =request.getData();

        Users user = Users.builder()
                .userid(userApiRequest.getUserid())
                .userpw(userApiRequest.getUserpw())
                .hp(userApiRequest.getHp())
                .email(userApiRequest.getEmail())
                .status(UserStatus.REGISTERD)
                .build();
        Users newUser = userRepository.save(user);
        return response(newUser);


    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        return userRepository.findById(id)
                .map(user -> response(user))
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest =request.getData(); //데이터를 가져온다
        Optional<Users> optional = userRepository.findById(userApiRequest.getId());//postman 에 넘어온 ID를 저장
        return optional.map(user ->{
          user.setUserid(userApiRequest.getUserid());
            user.setUserpw(userApiRequest.getUserpw());
            user.setHp(userApiRequest.getHp());
            user.setEmail(userApiRequest.getEmail());
            user.setStatus(userApiRequest.getStatus());

            return user;//set해서 유저로 리턴
        }).map(user -> userRepository.save(user))
                .map(user -> response(user))
                .orElseGet(() -> Header.ERROR("데이터없음"));
    }


    @Override
    public Header delete(Long id) {
        Optional<Users> optional = userRepository.findById(id);

        return optional.map(user -> {
            userRepository.delete(user);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }



    private Header<UserApiResponse> response(Users user){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .userid(user.getUserid())
                .userpw(user.getUserpw())
                .email(user.getEmail())
                .hp(user.getHp())
                .regDate(user.getRegDate())
                .status(user.getStatus())
                .build();
        return Header.OK(userApiResponse);
    }
}
