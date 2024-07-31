package com.intrument.saleintrument.web.service;

import com.intrument.saleintrument.web.models.Cart_Item;
import com.intrument.saleintrument.web.repository.CartItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Cart_ItemsService {
    @Autowired
    private CartItemsRepository cart_ItemsRepository;

    public Cart_Item saveCartItems(Cart_Item cart_Item) {
        return cart_ItemsRepository.save(cart_Item);
    }

    public Cart_Item getCartItemsByCartId(Long cartId) {
        return cart_ItemsRepository.findByCartId(cartId);
    }

    public boolean updateCartItems(Cart_Item cart_Item) {
        Cart_Item cart_Item1 = cart_ItemsRepository.findByCartId(cart_Item.getCart().getId());
        if (cart_Item1 != null) {
            cart_Item1.setProduct(cart_Item.getProduct());
            cart_Item1.setQuantity(cart_Item.getQuantity());
            cart_ItemsRepository.save(cart_Item1);
            return true;
        }
        return false;
    }

    public boolean deleteCartItemsByCartId(Long cartId) {
        Cart_Item cart_Item = cart_ItemsRepository.findByCartId(cartId);
        if (cart_Item != null) {
            cart_ItemsRepository.delete(cart_Item);
            return true;
        }
        return false;
    }
}
