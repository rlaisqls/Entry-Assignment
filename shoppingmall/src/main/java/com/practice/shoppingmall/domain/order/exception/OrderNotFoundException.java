package com.practice.shoppingmall.domain.order.exception;

import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.global.error.exception.ErrorCode;

public class OrderNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new OrderNotFoundException();

    private OrderNotFoundException() {
        super(ErrorCode.ORDER_NOT_FOUND);
    }
}