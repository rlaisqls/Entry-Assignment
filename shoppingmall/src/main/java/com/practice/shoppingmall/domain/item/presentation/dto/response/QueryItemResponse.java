package com.practice.shoppingmall.domain.item.presentation.dto.response;

import com.practice.shoppingmall.domain.item.domain.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryItemResponse {

    private Long itemId;

    private String itemName;

    public static QueryItemResponse of (Item item) {

        return QueryItemResponse
                .builder()
                .itemId(item.getId())
                .itemName(item.getName())
                .build();
    }
}