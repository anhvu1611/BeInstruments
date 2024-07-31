package com.intrument.saleintrument.web.dto.request;

import com.intrument.saleintrument.web.models.Cart_Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class CartRequest {
    private String username;
    private Set<CartItemRequest> cartItems;
}
