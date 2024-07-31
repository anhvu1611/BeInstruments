package com.intrument.saleintrument.web.service;

import com.intrument.saleintrument.web.models.Order;
import com.intrument.saleintrument.web.models.User;
import com.intrument.saleintrument.web.repository.OrderRepository;
import com.intrument.saleintrument.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order){
        order.setCreatedDate(LocalDate.now());
        order.setUpdatedDate(LocalDate.now());
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order){
        Order order1 = orderRepository.findById(id).orElse(null);
        assert order1 != null;
        order1.setUser(order.getUser());
        order1.setOrderDate(order.getOrderDate());
        order1.setStatus(order.getStatus());
        order1.setTotal(order.getTotal());
        order1.setAddress(order.getAddress());
        return orderRepository.save(order1);
    }

    public boolean deleteOrder(Long id){
        Order order = orderRepository.findById(id).orElse(null);
        if(order == null)
            return false;
        orderRepository.deleteById(id);
        return true;
    }


    public Double sumTotalByUser(User user){
        Double total = 0.0;
        List<Order> orders = orderRepository.findByUser(user);
        for (Order order : orders){
            total += order.getTotal();
        }
        return total;
    }

}
