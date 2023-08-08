package com.tranquilmagpie.spring.service.impl.shoporder;

import com.tranquilmagpie.spring.model.shoporder.ShopOrder;
import com.tranquilmagpie.spring.model.shoporder.ShopOrderItem;
import com.tranquilmagpie.spring.model.shoporder.ShopOrderStatus;
import com.tranquilmagpie.spring.repo.shoporder.ShopOrderItemRepo;
import com.tranquilmagpie.spring.repo.shoporder.ShopOrderRepo;
import com.tranquilmagpie.spring.service.shoporder.ShopOrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShopOrderItemServiceImpl implements ShopOrderItemService {

    private final ShopOrderItemRepo shopOrderItemRepo;
    private final ShopOrderRepo shopOrderRepo;

    public ShopOrderItemServiceImpl(ShopOrderItemRepo shopOrderItemRepo, ShopOrderRepo shopOrderRepo) {
        super();
        this.shopOrderItemRepo = shopOrderItemRepo;
        this.shopOrderRepo = shopOrderRepo;
    }

    // TODO: is this method needed
    @Override
    public List<ShopOrderItem> getAll() {
        return this.shopOrderItemRepo.findAll();
    }

    @Override
    public List<ShopOrderItem> getAllByShopOrderId(UUID id) {
        return this.shopOrderItemRepo.findByIdShopOrderId(id);
    }

    @Override
    public ShopOrderItem create(ShopOrderItem shopOrderItem) {
        UUID shopOrderId = shopOrderItem.getId().getShopOrderId();
        ShopOrder shopOrder = shopOrderRepo.findById(shopOrderId).get();
        ShopOrderStatus shopOrderStatus = shopOrder.getOrderStatus();

    // TODO: move this to patch request
        if (shopOrderStatus == ShopOrderStatus.PENDING ) {
            return this.shopOrderItemRepo.save(shopOrderItem);
        } else {
            // TODO: review how/where to provide response/feedback to client that the shop order item was not created
            return shopOrderItem;
        }
    }

    // TODO: patch request solely for updating order items while order status is PENDING
    //  read product name from DB and insert before save


}
