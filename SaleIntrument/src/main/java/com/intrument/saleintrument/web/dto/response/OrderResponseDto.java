package com.intrument.saleintrument.web.dto.response;

import com.intrument.saleintrument.web.models.Order;
import com.intrument.saleintrument.web.models.OrderDetailsProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Order order;
    private Long orderDetailsId;
    private List<OrderDetailsProduct> orderDetailsProducts;
}

