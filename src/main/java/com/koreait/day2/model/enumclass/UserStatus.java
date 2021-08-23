package com.koreait.day2.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    REGISTERD(0,"등록","사용자 가입상태"),
    UNREGISTERED(1,"탈퇴","사용자 탈퇴상태");

    private Integer id;
    private String title;
    private String description;

}
