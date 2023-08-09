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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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
    public ShopOrderItem updateOrderItem(ShopOrderItem shopOrderItem) {
        // Shop order details
        UUID shopOrderId = shopOrderItem.getId().getShopOrderId();
        ShopOrder shopOrder = shopOrderRepo.findById(shopOrderId).get();
        ShopOrderStatus shopOrderStatus = shopOrder.getOrderStatus();

        // Update shop order item
        if (shopOrderStatus == ShopOrderStatus.PENDING) {
            // TODO: product ID var?
            Product product = productRepo.findById(shopOrderItem.getId().getProductId()).get();

            // TODO: WIP! - revisit this once frontend creates shop order items before they are updated
            //  to avoid the need for existence check
            Optional<ShopOrderItem> existingShopOrderItem = shopOrderItemRepo.findById(shopOrderItem.getId());

            if (existingShopOrderItem.isEmpty() ) {
                productRepo.subtractStockQty(product.getId(), shopOrderItem.getQty());
            } else {
                int currentShopOrderItemQty = existingShopOrderItem.get().getQty();
                productRepo.addStockQty(product.getId(), currentShopOrderItemQty);
                productRepo.subtractStockQty(product.getId(), shopOrderItem.getQty());
            }

            shopOrderItem.setProductName(product.getName());
            shopOrderItem.setProductPrice(product.getPrice());
            shopOrderItem.setPriceTotal(
                    shopOrderItem.getProductPrice()
                            .multiply(new BigDecimal(shopOrderItem.getQty()))
            );

            // Update DB - shop order item
            ShopOrderItem savedShopOrderItem = this.shopOrderItemRepo.save(shopOrderItem);

            // Update DB - shop order total
            BigDecimal currentOrderTotal = shopOrderItemRepo.getPriceTotalByShopOrderId(shopOrderId);
            shopOrderRepo.updateOrderTotal(shopOrderId, currentOrderTotal);

            return savedShopOrderItem;
        } else {
            // TODO: review how/where to provide response/feedback to client that the shop order item was not updated
            return shopOrderItem;
        }
    }

    @Override
    public BigDecimal getPriceTotalByShopOrderId(UUID id) {
        return shopOrderItemRepo.getPriceTotalByShopOrderId(id);
    }

}
