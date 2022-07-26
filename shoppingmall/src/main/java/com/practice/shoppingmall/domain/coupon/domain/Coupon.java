package com.practice.shoppingmall.domain.coupon.domain;

import com.practice.shoppingmall.domain.coupon.presentation.dto.request.CreateCouponRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "discountType", discriminatorType = DiscriminatorType.STRING)
@Entity
public abstract class Coupon {

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private final List<UserCoupon> users = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @NotNull
    @Size(max = 30)
    private String couponName;

    @NotNull
    private int validityPeriod;

    public abstract int doDiscount(int totalPrice);

    public abstract String getDiscountAmount();

    public static Coupon of(CreateCouponRequest request) {

        Coupon coupon;
        switch (request.getDiscountType()){
            case FIXED:
                coupon = FixedDiscountCoupon.couponBuild(request); break;
            case RATE:
                coupon = RateDiscountCoupon.couponBuild(request); break;
            default:
                throw new IllegalStateException();
        }
        return coupon;
    }
}