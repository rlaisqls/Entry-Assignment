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
@DiscriminatorValue(DiscountType.Values.FIXED)
@Entity
public class FixedDiscountCoupon extends Coupon {

    @NotNull
    private Integer discountPrice;

    @Override
    public int doDiscount(int totalPrice) {
        if (totalPrice < this.discountPrice) return 0;
        else return totalPrice - this.discountPrice;
    }

    @Override
    public String getDiscountAmount() {
        return discountPrice + "원";
    }

    public static FixedDiscountCoupon couponBuild(CreateCouponRequest request) {

        return FixedDiscountCoupon.builder()
                .couponName(request.getCouponName())
                .discountPrice(request.getDiscountAmount())
                .validityPeriod(request.getValidityPeriod())
                .build();
    }
}