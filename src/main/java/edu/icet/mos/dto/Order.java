package edu.icet.mos.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private Integer id;
    private Integer customerId;
    private List<OrderDetail> orderDetailList;
    private LocalDate date;
    private LocalTime time;
    private Double amount;
}
