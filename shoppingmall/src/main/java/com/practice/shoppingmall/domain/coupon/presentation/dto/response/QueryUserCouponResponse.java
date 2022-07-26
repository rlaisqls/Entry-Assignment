package com.practice.shoppingmall.domain.coupon.presentation.dto.response;

import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.enums.DiscountType;
import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class QueryUserCouponResponse {
    private Long couponId;

    private String couponName;

    private DiscountType discountType;

    private String discountAmount;

    private LocalDateTime expirationDateTime;

    public static QueryUserCouponResponse of(UserCoupon userCoupon) {

        Coupon coupon = userCoupon.getCoupon();

        return QueryUserCouponResponse
                .builder()
                .couponId(userCoupon.getId())
                .couponName(coupon.getCouponName())
                .discountAmount(coupon.getDiscountAmount())
                .expirationDateTime(userCoupon.getExpirationDateTime())
                .build();
    }
}