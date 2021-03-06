package com.koreait.day2.model.network.request;

import com.koreait.day2.model.enumclass.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(
        name = "seq_group",
        sequenceName = "seq_group",
        initialValue = 1,
        allocationSize = 1
)
public class OrderGroupApiRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="seq_group" )
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    private String status;
    private String revAddress;
    private String revName;
    private String paymentType;
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private LocalDateTime orderAt;
    private LocalDateTime arrivalDate;
    @CreatedDate
    private LocalDateTime regDate;
}
