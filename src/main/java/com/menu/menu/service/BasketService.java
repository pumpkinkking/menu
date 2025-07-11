package com.menu.menu.service;

import com.menu.menu.dto.BasketDTO;
import com.menu.menu.vo.BasketVO;
import java.util.List;

public interface BasketService {
    List<BasketVO> getBasketItemsByUserId(Long userId);
    Long addToBasket(BasketDTO basketDTO, Long userId);
    boolean removeFromBasket(Long ingredientId, Long userId);
}