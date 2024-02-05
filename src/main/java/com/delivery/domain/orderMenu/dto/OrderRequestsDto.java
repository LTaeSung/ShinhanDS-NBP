package com.delivery.domain.orderMenu.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class OrderRequestsDto {
    private ArrayList<OrderMenuDto> orderRequests;
}
