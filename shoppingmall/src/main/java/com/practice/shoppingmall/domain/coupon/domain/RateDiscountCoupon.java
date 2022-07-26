package com.practice.shoppingmall.domain.coupon.domain;

import com.practice.shoppingmall.domain.coupon.domain.enums.DiscountType;
import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(DiscountType.Values.RATE)
@Entity
public class RateDiscountCoupon extends Coupon {

    @NotNull
    private Integer discountRate;

    @Override
    public int doDiscount(int totalPrice) {
        return totalPrice - (totalPrice * this.discountRate / 100);
    }

    @Override
    public String getDiscountAmount() {
        return discountRate + "%";
    }

    public static RateDiscountCoupon couponBuild(CreateCouponRequest request) {

        if(100 < request.getDiscountAmount())
            throw new IllegalStateException();

        return RateDiscountCoupon.builder()
                .couponName(request.getCouponName())
                .discountRate(request.getDiscountAmount())
                .validityPeriod(request.getValidityPeriod())
                .build();
    }
}