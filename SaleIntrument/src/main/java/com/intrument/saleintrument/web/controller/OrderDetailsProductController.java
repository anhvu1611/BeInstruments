package com.intrument.saleintrument.web.controller;

import com.intrument.saleintrument.web.models.OrderDetailsProduct;
import com.intrument.saleintrument.web.service.OrderDetailsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-details-product")
public class OrderDetailsProductController {
    @Autowired
    private OrderDetailsProductService orderDetailsProductService;

    public ResponseEntity<OrderDetailsProduct> createOrderDetailsProduct(OrderDetailsProduct orderDetailsProduct){
        return ResponseEntity.ok(orderDetailsProductService.createOrderDetailsProduct(orderDetailsProduct));
    }

    public List<OrderDetailsProduct> saveAll(List<OrderDetailsProduct> orderDetailsProducts){
        return orderDetailsProductService.createOrderDetailsProducts(orderDetailsProducts);
    }

    public ResponseEntity<OrderDetailsProduct> getOrderDetailsProductById(Long id){
        return ResponseEntity.ok(orderDetailsProductService.getOrderDetailsProductById(id));
    }

    public ResponseEntity<Boolean> deleteOrderDetailsProduct(Long id){
        return ResponseEntity.ok(orderDetailsProductService.deleteOrderDetailsProduct(id));
    }

    @GetMapping("/{orderDetailsId}")
    public ResponseEntity<?> getAllOrderDetailsProductsByOrderId(@PathVariable Long orderDetailsId){
        return ResponseEntity.ok(orderDetailsProductService.getAllOrderDetailsProductsByOrderId(orderDetailsId));
    }
}
