package com.intrument.saleintrument.web.controller;

import com.intrument.saleintrument.web.models.Order;
import com.intrument.saleintrument.web.models.Order_Details;
import com.intrument.saleintrument.web.repository.OrderRepository;
import com.intrument.saleintrument.web.service.OrderDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/order-details")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsServices orderDetailsServices;
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<Order_Details> createOrderDetails(Long orderID){
        Order order = orderRepository.findById(orderID).orElse(null);
        Order_Details order_details = new Order_Details();
        order_details.setOrder(order);
        return ResponseEntity.ok(orderDetailsServices.createOrderDetails(order_details));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order_Details> getOrderDetailsById(Long id){
        return ResponseEntity.ok(orderDetailsServices.getOrderDetailsById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOrderDetails(Long id){
        return ResponseEntity.ok(orderDetailsServices.deleteOrderDetails(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllOrderDetails() {
        return ResponseEntity.ok(orderDetailsServices.getAllOrderDetails());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getAllOrderDetailsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderDetailsServices.getAllOrderDetailsByOrderId(orderId));
    }

}
