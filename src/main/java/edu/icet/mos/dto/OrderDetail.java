package edu.icet.mos.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {
    private Integer id;
    private Integer orderId;
    private Integer itemId;
    private Integer qty;
    private Double amount;
}
