package com.tranquilmagpie.spring.service.impl.shoporder;

import com.tranquilmagpie.spring.model.product.Product;
import com.tranquilmagpie.spring.model.shoporder.ShopOrder;
import com.tranquilmagpie.spring.model.shoporder.ShopOrderItem;
import com.tranquilmagpie.spring.model.shoporder.ShopOrderStatus;
import com.tranquilmagpie.spring.repo.product.ProductRepo;
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
    private final ProductRepo productRepo;

    public ShopOrderItemServiceImpl(ShopOrderItemRepo shopOrderItemRepo, ShopOrderRepo shopOrderRepo, ProductRepo productRepo) {
        super();
        this.shopOrderItemRepo = shopOrderItemRepo;
        this.shopOrderRepo = shopOrderRepo;
        this.productRepo = productRepo;
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
        return this.shopOrderItemRepo.save(shopOrderItem);
    }

    @Override
    public ShopOrderItem updateOrderItems(ShopOrderItem shopOrderItem) {
        UUID shopOrderId = shopOrderItem.getId().getShopOrderId();
        ShopOrder shopOrder = shopOrderRepo.findById(shopOrderId).get();
        ShopOrderStatus shopOrderStatus = shopOrder.getOrderStatus();

        if (shopOrderStatus == ShopOrderStatus.PENDING) {
            Product product = productRepo.findById(shopOrderItem.getId().getProductId()).get();

            shopOrderItem.setProductName(product.getName());
            shopOrderItem.setProductPrice(product.getPrice());

            return this.shopOrderItemRepo.save(shopOrderItem);
        } else {
            // TODO: review how/where to provide response/feedback to client that the shop order item was not updated
            return shopOrderItem;
        }
    }


}
