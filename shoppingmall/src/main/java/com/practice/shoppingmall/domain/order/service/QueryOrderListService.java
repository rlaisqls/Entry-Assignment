package com.practice.shoppingmall.domain.order.service;

import com.practice.shoppingmall.domain.order.domain.repository.OrderRepository;
import com.practice.shoppingmall.domain.order.presentation.dto.response.QueryOrderResponse;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryOrderListService {

    private final UserFacade userFacade;

    private final OrderRepository orderRepository;

    public List<QueryOrderResponse> execute() {

        User user = userFacade.getCurrentUser();

        return orderRepository.findByUser(user)
                .stream()
                .map(QueryOrderResponse::of)
                .collect(Collectors.toList());
    }
}