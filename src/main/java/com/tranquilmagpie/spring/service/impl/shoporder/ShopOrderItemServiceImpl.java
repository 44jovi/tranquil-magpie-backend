package com.tranquilmagpie.spring.service.impl.shoporder;

import com.tranquilmagpie.spring.model.shoporder.ShopOrderItem;
import com.tranquilmagpie.spring.repo.shoporder.ShopOrderItemRepo;
import com.tranquilmagpie.spring.service.shoporder.ShopOrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShopOrderItemServiceImpl implements ShopOrderItemService {

    private final ShopOrderItemRepo shopOrderItemRepo;

    public ShopOrderItemServiceImpl(ShopOrderItemRepo shopOrderItemRepo) {
        super();
        this.shopOrderItemRepo = shopOrderItemRepo;
    }

    @Override
    public List<ShopOrderItem> getAllByShopOrderId(UUID id) {
        return this.shopOrderItemRepo.findAllByShopOrderId(id).get();
    }

}
