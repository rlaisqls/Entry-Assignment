package com.practice.shoppingmall.domain.order.service;

import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.repository.OrderRepository;
import com.practice.shoppingmall.domain.order.facade.OrderFacade;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.exception.ForbiddenUserException;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CancelOrderService {

    private final UserFacade userFacade;
    private final OrderFacade orderFacade;
    private final OrderRepository orderRepository;

    @Transactional
    public void execute(Long id) {

        User user = userFacade.getCurrentUser();
        Order order = orderFacade.getOrderById(id);

        if (order.getUser() != user){
            throw ForbiddenUserException.EXCEPTION;
        }

        order.cancel();

        orderRepository.save(order);
    }
}