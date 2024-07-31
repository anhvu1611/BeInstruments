package com.intrument.saleintrument.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class OrderRequest {
    private String username;
    private String status;
    private Double total;
    private String address;
    private List<OrderDetailsProductRequest> orderDetailsProductRequests;
}
