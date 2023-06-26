package com.example.smartphone_web.dto.request.orderinvoice;

import com.example.smartphone_web.dto.request.orderinvoicedetail.OrderInvoiceDetailRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInvoiceRequest {
    private String id;
    private String note;
    private String orderCode;
    private Long totalMoney;
    private String receiveDate;
    private Integer suppliderId;
    private Long discount;
    private Long paid;
    private Long tienThua;
    private String status;
    private List<OrderInvoiceDetailRequest> detailRequest;
}
