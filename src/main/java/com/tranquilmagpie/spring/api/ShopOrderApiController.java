package com.tranquilmagpie.spring.api;

import com.tranquilmagpie.spring.model.ShopOrder;
import com.tranquilmagpie.spring.service.ShopOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/orders")
public class ShopOrderApiController {

    private final ShopOrderService service;

    public ShopOrderApiController(ShopOrderService service) {
        super();
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<ShopOrder>> getAll() {
        List<ShopOrder> shopOrders = this.service.getAll();
        return new ResponseEntity<>(shopOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopOrder> getById(@PathVariable UUID id) {
        ShopOrder shopOrder = this.service.getById(id);
        return new ResponseEntity<>(shopOrder, HttpStatus.OK);
    }

    @GetMapping("/user-id/{id}")
    public ResponseEntity<List<ShopOrder>> getAllByUserId(@PathVariable UUID id) {
        List<ShopOrder> shopOrder = this.service.getAllByUserId(id);
        return new ResponseEntity<>(shopOrder, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ShopOrder> create(@RequestBody ShopOrder shopOrder) {
        ShopOrder createdShopOrder = this.service.create(shopOrder);
        return new ResponseEntity<>(createdShopOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ShopOrder> deleteById(@PathVariable UUID id) {
        ShopOrder deletedShopOrder = this.service.deleteById(id);
        return new ResponseEntity<>(deletedShopOrder, HttpStatus.OK);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<ShopOrder> patchById(@PathVariable UUID id, @RequestBody ShopOrder shopOrder) {
        ShopOrder patchedShopOrder = this.service.patchById(id, shopOrder);
        return new ResponseEntity<>(patchedShopOrder, HttpStatus.OK);
    }
}
