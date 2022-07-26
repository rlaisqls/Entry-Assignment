package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.presentation.dto.response.QueryItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryItemListService {

    private final ItemRepository itemRepository;

    public List<QueryItemResponse> execute() {

        return itemRepository.findBy()
                .stream()
                .map(QueryItemResponse::of)
                .collect(Collectors.toList());
    }
}