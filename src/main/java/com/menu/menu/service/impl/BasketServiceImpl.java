package com.menu.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.menu.menu.dto.BasketDTO;
import com.menu.menu.entity.Basket;
import com.menu.menu.mapper.BasketMapper;
import com.menu.menu.service.BasketService;
import com.menu.menu.vo.BasketVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketMapper basketMapper;

    @Override
    public List<BasketVO> getBasketItemsByUserId(Long userId) {
        QueryWrapper<Basket> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Basket> basketItems = basketMapper.selectList(queryWrapper);
        return basketItems.stream().map(this::convertToVO).collect(Collectors.toList());
    }

    @Override
    public Long addToBasket(BasketDTO basketDTO, Long userId) {
        // 检查是否已存在该食材，存在则更新数量
        QueryWrapper<Basket> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                    .eq("ingredient_id", basketDTO.getIngredientId());
        Basket existingItem = basketMapper.selectOne(queryWrapper);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + basketDTO.getQuantity());
            existingItem.setUpdateTime(LocalDateTime.now());
            basketMapper.updateById(existingItem);
            return existingItem.getId();
        }

        // 不存在则新增
        Basket basket = new Basket();
        BeanUtils.copyProperties(basketDTO, basket);
        basket.setUserId(userId);
        basket.setCreateTime(LocalDateTime.now());
        basket.setUpdateTime(LocalDateTime.now());
        basketMapper.insert(basket);
        return basket.getId();
    }

    @Override
    public boolean removeFromBasket(Long ingredientId, Long userId) {
        QueryWrapper<Basket> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                    .eq("ingredient_id", ingredientId);
        int rows = basketMapper.delete(queryWrapper);
        return rows > 0;
    }

    private BasketVO convertToVO(Basket basket) {
        BasketVO vo = new BasketVO();
        BeanUtils.copyProperties(basket, vo);
        return vo;
    }
}