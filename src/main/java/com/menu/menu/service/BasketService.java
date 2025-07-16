package com.menu.menu.service;

import com.menu.menu.dto.BasketDTO;
import com.menu.menu.vo.BasketVO;
import java.util.List;

public interface BasketService {
    List<BasketVO> getBasketItemsByUserId(String userId);
    Integer addToBasket(BasketDTO basketDTO, String userId);
    boolean removeFromBasket(Integer ingredientId, String userId);
}