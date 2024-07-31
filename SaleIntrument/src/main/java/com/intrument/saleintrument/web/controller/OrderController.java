package com.intrument.saleintrument.web.controller;

import com.intrument.saleintrument.admin.service.EmailService;
import com.intrument.saleintrument.web.dto.request.OrderDetailsProductRequest;
import com.intrument.saleintrument.web.dto.request.OrderRequest;
import com.intrument.saleintrument.web.dto.response.OrderResponseDto;
import com.intrument.saleintrument.web.models.Order;
import com.intrument.saleintrument.web.models.OrderDetailsProduct;
import com.intrument.saleintrument.web.models.Order_Details;
import com.intrument.saleintrument.web.models.Product;
import com.intrument.saleintrument.web.repository.*;
import com.intrument.saleintrument.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrderDetailsProductRepository orderDetailsProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            Order order = new Order();
            order.setUser(userRepository.findByUsername(request.getUsername()));
            order.setStatus(request.getStatus());
            order.setTotal(request.getTotal());
            order.setAddress(request.getAddress());
            order.setOrderDate(java.time.LocalDate.now());
            order.setCreatedDate(java.time.LocalDate.now());
            order.setUpdatedDate(java.time.LocalDate.now());

            // Save Order
            Order savedOrder = orderService.createOrder(order);

            // Create and save OrderDetails
            Order_Details orderDetails = new Order_Details();
            orderDetails.setOrder(savedOrder);
            Order_Details savedOrderDetails = orderDetailsRepository.save(orderDetails);

            // Create and save OrderDetailsProducts
            List<OrderDetailsProductRequest> orderDetailsProducts = request.getOrderDetailsProductRequests();
            for (OrderDetailsProductRequest orderDetailsProductRequest : orderDetailsProducts) {
                OrderDetailsProduct orderDetailsProduct = new OrderDetailsProduct();
                orderDetailsProduct.setOrderDetails(savedOrderDetails); // Use savedOrderDetails here
                Product product = productRepository.findById(orderDetailsProductRequest.getIdProduct()).orElse(null);
                orderDetailsProduct.setProduct(product);
                orderDetailsProduct.setQuantity(orderDetailsProductRequest.getQuantity());
                assert product != null;
                orderDetailsProduct.setPrice(product.getPrice());
                orderDetailsProductRepository.save(orderDetailsProduct);
            }
            emailService.sendOrderConfirmation(order.getUser().getEmail(), savedOrder);
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while processing the order: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        Optional<Order> order = Optional.ofNullable(orderService.getOrderById(id));
        Order_Details orderDetails = orderDetailsRepository.findOrder_DetailsByOrderId(id);
        List<OrderDetailsProduct> orderDetailsProducts = orderDetailsProductRepository.findByOrderDetailsId(orderDetails.getId());
        if (order.isPresent()) {
            OrderResponseDto responseDto = new OrderResponseDto(order.get(), orderDetails.getId(), orderDetailsProducts);
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.status(404).body("Order not found.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<Order> orders = orderService.getAllOrder();
        orders.sort(Comparator.comparing(Order::getCreatedDate).reversed());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getAllOrdersByUser(@PathVariable String username) {
        List<Order> orders = orderRepository.findByUser(userRepository.findByUsername(username));
        orders.sort(Comparator.comparing(Order::getCreatedDate).reversed());
        return ResponseEntity.ok(orders);
    }

}

