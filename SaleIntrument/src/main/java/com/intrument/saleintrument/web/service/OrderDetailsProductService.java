package com.intrument.saleintrument.web.service;

import com.intrument.saleintrument.web.models.OrderDetailsProduct;
import com.intrument.saleintrument.web.repository.OrderDetailsProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsProductService {
    @Autowired
    OrderDetailsProductRepository orderDetailsProductRepository;

    public OrderDetailsProduct createOrderDetailsProduct(OrderDetailsProduct orderDetailsProduct){
        return orderDetailsProductRepository.save(orderDetailsProduct);
    }

    public OrderDetailsProduct getOrderDetailsProductById(Long id) {
        return orderDetailsProductRepository.findById(id).orElse(null);
    }

    public boolean deleteOrderDetailsProduct(Long id) {
        OrderDetailsProduct orderDetailsProduct = orderDetailsProductRepository.findById(id).orElse(null);
        if (orderDetailsProduct == null)
            return false;
        orderDetailsProductRepository.deleteById(id);
        return true;
    }

    public List<OrderDetailsProduct> createOrderDetailsProducts(List<OrderDetailsProduct> orderDetailsProducts) {
        return orderDetailsProductRepository.saveAll(orderDetailsProducts);
    }

    public List<OrderDetailsProduct> getAllOrderDetailsProductsByOrderId(Long orderDetailsId) {
        return orderDetailsProductRepository.findByOrderDetailsId(orderDetailsId);
    }
}
