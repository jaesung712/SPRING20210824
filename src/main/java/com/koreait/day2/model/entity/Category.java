package com.koreait.day2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name="seq_cate",
        sequenceName = "seq_cate",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cate")
    private Long id;
    private String type;
    private String title;
    @CreatedDate
    private LocalDateTime regDate;

    @LastModifiedDate //마지막바뀐시간
    private LocalDateTime updateDate;

    @LastModifiedBy
    private String updateBy;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "category")
    private List<Partner> partnerList;//한번의 쿼리로 한번에 파트너의 리스트를 가져옴


}
