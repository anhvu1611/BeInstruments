package com.intrument.saleintrument.web.service;

import com.intrument.saleintrument.web.models.Order_Details;
import com.intrument.saleintrument.web.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServices {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public Order_Details createOrderDetails(Order_Details order_details){
        return orderDetailsRepository.save(order_details);
    }

    public Order_Details getOrderDetailsById(Long id){
        return orderDetailsRepository.findById(id).orElse(null);
    }

    public boolean deleteOrderDetails(Long id){
        Order_Details order_details = orderDetailsRepository.findById(id).orElse(null);
        if(order_details == null)
            return false;
        orderDetailsRepository.deleteById(id);
        return true;
    }

    public List<Order_Details> getAllOrderDetails(){
        return orderDetailsRepository.findAll();
    }

    public Order_Details getAllOrderDetailsByOrderId(Long orderId){
        return orderDetailsRepository.findOrder_DetailsByOrderId(orderId);
    }
}
