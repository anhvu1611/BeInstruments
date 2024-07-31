package com.intrument.saleintrument.web.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Category extends AbstractModel{
    private String name;
    private String description;

//    @OneToMany(mappedBy = "category")
//    private List<Product> products;
}
