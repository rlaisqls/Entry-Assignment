package com.practice.shoppingmall.domain.coupon.service;

import com.practice.shoppingmall.domain.coupon.domain.repository.UserCouponRepository;
import com.practice.shoppingmall.domain.coupon.facade.UserCouponFacade;
import com.practice.shoppingmall.domain.coupon.presentation.dto.response.QueryCouponResponse;
import com.practice.shoppingmall.domain.user.domain.User;
import com.practice.shoppingmall.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryMyCouponListService {

    private final UserFacade userFacade;

    private final UserCouponFacade userCouponFacade;

    private final UserCouponRepository userCouponRepository;

    public List<QueryCouponResponse> execute() {

        User user = userFacade.getCurrentUser();

        return userCouponRepository.findByUser(user)
                .stream()
                .filter(userCouponFacade::validateCoupon)
                .map(QueryCouponResponse::of)
                .collect(Collectors.toList());
    }
}