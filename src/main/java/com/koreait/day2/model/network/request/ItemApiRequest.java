package com.koreait.day2.model.network.request;

import com.koreait.day2.model.enumclass.ItemStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class ItemApiRequest {


        private Long id;
        private String name;
        private ItemStatus status;
        private String title;
        private String content;
        private BigDecimal price;
        private LocalDateTime regDate;
        private String createBy;
        private LocalDateTime updateDate;
        private String updateBy;

    }