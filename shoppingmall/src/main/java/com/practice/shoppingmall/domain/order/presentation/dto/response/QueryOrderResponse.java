package com.practice.shoppingmall.domain.order.presentation.dto.response;

import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.OrderItem;
import com.practice.shoppingmall.domain.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class QueryOrderResponse {

    private Long orderId;

    private OrderStatus orderStatus;

    private LocalDate orderDate;

    private String representativeItemName;

    private int totalItemCount;

    public static QueryOrderResponse of(Order order){

        List<OrderItem> orderItems = order.getOrderItems();

        return QueryOrderResponse
                .builder()
                .orderId(order.getId())
                .representativeItemName(orderItems.get(0).getItem().getName())
                .totalItemCount(orderItems.size())
                .orderStatus(order.getOrderStatus())
                .orderDate(order.getOrderDateTime().toLocalDate())
                .build();
    }
}