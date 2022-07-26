package com.practice.shoppingmall.domain.order.service;

import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import com.practice.shoppingmall.domain.coupon.domain.repository.UserCouponRepository;
import com.practice.shoppingmall.domain.coupon.exception.InvalidCouponException;
import com.practice.shoppingmall.domain.coupon.facade.UserCouponFacade;
import com.practice.shoppingmall.domain.delivery.domain.Delivery;
import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.exception.ItemNotFoundException;
import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.order.domain.OrderItem;
import com.practice.shoppingmall.domain.order.domain.repository.OrderRepository;
import com.practice.shoppingmall.domain.order.presentation.dto.request.OrderItemRequest;
import com.practice.shoppingmall.domain.order.presentation.dto.response.CreateOrderResponse;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreateOrderService {

    private final UserFacade userFacade;

    private final UserCouponFacade userCouponFacade;

    private final OrderRepository orderRepository;

    private final UserCouponRepository userCouponRepository;

    private final ItemRepository itemRepository;

    @Transactional
    public CreateOrderResponse execute(List<OrderItemRequest> requests) {

        User user = userFacade.getCurrentUser();

        Delivery delivery = Delivery.start(user);

        List<OrderItem> orderItems = requests
                .stream()
                .map(this::createOrderItem)
                .collect(Collectors.toList());

        Order order = orderRepository.save(Order.createOrder(user, delivery, orderItems));

        return CreateOrderResponse
                .builder()
                .orderId(order.getId())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    private OrderItem createOrderItem(OrderItemRequest request) {

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        item.removeStock(request.getCount());

        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .count(request.getCount())
                .orderPrice(item.getPrice())
                .totalPrice(item.getPrice() * request.getCount())
                .build();

        if (request.getUserCouponId() != null) {

            UserCoupon userCoupon = validateAndGetCoupon(request.getUserCouponId());
            orderItem.applyCoupon(userCoupon.getCoupon());
            userCouponRepository.delete(userCoupon);
        }

        return orderItem;
    }

    private UserCoupon validateAndGetCoupon(Long userCouponId) {

        User user = userFacade.getCurrentUser();

        UserCoupon userCoupon = userCouponFacade.getUserCoupon(userCouponId, user);

        if (!userCouponFacade.validateCoupon(userCoupon))
            throw InvalidCouponException.EXCEPTION;

        return userCoupon;
    }

}