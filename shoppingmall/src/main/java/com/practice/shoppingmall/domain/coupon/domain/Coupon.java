package com.practice.shoppingmall.domain.coupon.domain;

import com.practice.shoppingmall.domain.coupon.exception.InvalidCouponException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

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
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private CouponDiscountType discountType;

    @NotNull
    private int discountAmount;

    @NotNull
    private int validityPeriod;

    public int doDiscount(int totalPrice) {

        switch (this.discountType) {
            case RATE:
                return totalPrice - (totalPrice * this.discountAmount / 100);
            case FIXED:
                if (totalPrice < this.discountAmount) return 0;
                else return totalPrice - this.discountAmount;
            default:
                throw InvalidCouponException.EXCEPTION;
        }
    }

    public String getUnit() {
        switch (this.discountType) {
            case RATE:
                return "%";
            case FIXED:
                return "원";
            default:
                throw InvalidCouponException.EXCEPTION;
        }
    }
}