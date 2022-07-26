package com.practice.shoppingmall.domain.coupon.service;

import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.coupon.domain.CouponDiscountType;
import com.practice.shoppingmall.domain.coupon.domain.repository.CouponRepository;
import com.practice.shoppingmall.domain.coupon.exception.DiscountOutOfRangeException;
import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.CreateCouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCouponService {

    private final CouponRepository couponRepository;

    public CreateCouponResponse execute(CreateCouponRequest request) {

        couponValidate(request);

        Coupon coupon = couponRepository.save(Coupon.builder()
                .couponName(request.getCouponName())
                .discountType(request.getDiscountType())
                .discountAmount(request.getDiscountAmount())
                .validityPeriod(request.getValidityPeriod())
                .build());

        return new CreateCouponResponse(coupon.getId());
    }

    private void couponValidate(CreateCouponRequest request) {

        if (request.getDiscountType() == CouponDiscountType.RATE && request.getDiscountAmount() >= 100)
            throw DiscountOutOfRangeException.EXCEPTION;
    }
}