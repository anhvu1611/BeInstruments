package com.intrument.saleintrument.web.controller;

import com.intrument.saleintrument.web.dto.request.CartItemRequest;
import com.intrument.saleintrument.web.dto.request.CartRequest;
import com.intrument.saleintrument.web.models.Cart;
import com.intrument.saleintrument.web.models.Cart_Item;
import com.intrument.saleintrument.web.models.User;
import com.intrument.saleintrument.web.repository.CartRepository;
import com.intrument.saleintrument.web.repository.ProductRepository;
import com.intrument.saleintrument.web.repository.UserRepository;
import com.intrument.saleintrument.web.service.CartService;
import com.intrument.saleintrument.web.service.Cart_ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("api/cart")
public class CartController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private Cart_ItemsService cartItemsService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<?> createCart(@RequestBody CartRequest request) {
        try {
            User user = userRepository.findByUsername(request.getUsername());
            Cart cart = cartRepository.findByUser(user);
            if (cart == null) {
                Cart cart1 = new Cart();
                cart1.setUser(user);
                cartService.saveCart(cart1);
            }

            Set<CartItemRequest> cartItems = request.getCartItems();
            for (CartItemRequest itemRequest : cartItems) {
                Cart_Item cartItem = new Cart_Item();
                cartItem.setCart(cart); // Link to the saved cart
                cartItem.setProduct(productRepository.findById(itemRequest.getProductId()).orElse(null));
                cartItem.setQuantity(itemRequest.getQuantity());
                cartItemsService.saveCartItems(cartItem);
            }


            return ResponseEntity.ok("Cart created successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}