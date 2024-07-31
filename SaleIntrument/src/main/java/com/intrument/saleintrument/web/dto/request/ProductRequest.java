package com.intrument.saleintrument.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductRequest{
    private String name;
    private String description;
    private Double price;
    private Integer stock_quantity;
    private String categoryName;
    private byte[] image;

}
