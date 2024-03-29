package com.tranquilmagpie.spring.api.shoporder;

import com.tranquilmagpie.spring.model.shoporder.ShopOrderItem;
import com.tranquilmagpie.spring.service.shoporder.ShopOrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop-order-item")
public class ShopOrderItemApiController {

    private final ShopOrderItemService service;

    public ShopOrderItemApiController(ShopOrderItemService service) {
        super();
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<ShopOrderItem>> getAll() {
        List<ShopOrderItem> shopOrderItem = this.service.getAll();
        return new ResponseEntity<>(shopOrderItem, HttpStatus.OK);
    }

    @GetMapping("/order-id/{id}")
    public ResponseEntity<List<ShopOrderItem>> getAllByShopOrderId(@PathVariable UUID id) {
        List<ShopOrderItem> shopOrderItem = this.service.getAllByShopOrderId(id);
        return new ResponseEntity<>(shopOrderItem, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ShopOrderItem> create(@RequestBody ShopOrderItem shopOrderItem) {
        ShopOrderItem createdShopOrderItem = this.service.create(shopOrderItem);
        return new ResponseEntity<>(createdShopOrderItem, HttpStatus.CREATED);
    }

    @PatchMapping("/update-items")
    public ResponseEntity<ShopOrderItem> updateOrderItem(@RequestBody ShopOrderItem shopOrderItem) {
        ShopOrderItem createdShopOrderItem = this.service.updateOrderItem(shopOrderItem);
        return new ResponseEntity<>(createdShopOrderItem, HttpStatus.OK);
    }

    @GetMapping("/price-total/order-id/{id}")
    public ResponseEntity<BigDecimal> getPriceTotalByShopOrderId(@PathVariable UUID id) {
        BigDecimal priceTotal = this.service.getPriceTotalByShopOrderId(id);
        return new ResponseEntity<>(priceTotal, HttpStatus.OK);
    }

}
