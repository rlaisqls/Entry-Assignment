package com.practice.shoppingmall.domain.coupon.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DiscountType {

    FIXED(Values.FIXED),
    RATE(Values.RATE);

    private final String value;

    public static class Values {
        public static final String FIXED = "FIXED";
        public static final String RATE = "RATE";
    }
}