package com.practice.shoppingmall.domain.coupon.presentation.dto.response;

import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.CouponDiscountType;
import com.practice.shoppingmall.domain.coupon.domain.UserCoupon;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class QueryCouponResponse {
    private Long couponId;

    private String couponName;

    private CouponDiscountType discountType;

    private String discountAmount;

    private LocalDateTime expirationDateTime;

    public static QueryCouponResponse of(UserCoupon userCoupon) {

        Coupon coupon = userCoupon.getCoupon();

        return QueryCouponResponse
                .builder()
                .couponId(userCoupon.getId())
                .couponName(coupon.getCouponName())
                .discountType(coupon.getDiscountType())
                .discountAmount(coupon.getDiscountAmount() + coupon.getUnit())
                .expirationDateTime(userCoupon.getExpirationDateTime())
                .build();
    }
}