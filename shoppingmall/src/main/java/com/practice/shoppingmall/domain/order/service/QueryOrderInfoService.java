package com.practice.shoppingmall.domain.order.service;

import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.facade.OrderFacade;
import com.practice.shoppingmall.domain.order.presentation.dto.response.QueryOrderInfoResponse;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.exception.ForbiddenUserException;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class QueryOrderInfoService {

    private final UserFacade userFacade;

    private final OrderFacade orderFacade;

    public QueryOrderInfoResponse execute(Long orderId) {

        User user = userFacade.getCurrentUser();

        Order order = orderFacade.getOrderById(orderId);

        if (order.getUser() != user) throw ForbiddenUserException.EXCEPTION;

        return QueryOrderInfoResponse.of(order);
    }
}