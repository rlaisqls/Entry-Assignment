package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.facade.ItemFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteItemService {

    private final ItemRepository itemRepository;

    private final ItemFacade itemFacade;

    @Transactional
    public void execute(Long itemId) {

        Item item = itemFacade.getItemById(itemId);

        itemRepository.delete(item);
    }
}