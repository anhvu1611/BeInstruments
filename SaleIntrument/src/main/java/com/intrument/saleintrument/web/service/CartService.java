package com.intrument.saleintrument.web.service;

import com.intrument.saleintrument.web.models.Cart;
import com.intrument.saleintrument.web.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public boolean deleteCart(Long cartID) {
        Cart cart = cartRepository.findById(cartID).orElse(null);
        if (cart == null) {
            return false;
        }
        cartRepository.delete(cart);
        return true;
    }

    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}
