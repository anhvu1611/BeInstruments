package com.intrument.saleintrument.web.service;

import com.intrument.saleintrument.web.models.Product;
import com.intrument.saleintrument.web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }


    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }


    public Product updateProduct(Long id, Product product){
        Product product1 = productRepository.findById(id).orElse(null);
        assert product1 != null;
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        product1.setStock_quantity(product.getStock_quantity());
        product1.setImage(product.getImage());
        product1.setCategory(product.getCategory());
        product1.setUpdatedDate(LocalDate.now());
        return productRepository.save(product1);
    }

    public boolean deleteProduct(Long id){
        Product product = productRepository.findById(id).orElse(null);
        if(product == null)
            return false;
        productRepository.deleteById(id);
        return true;
    }

    public List<Product> getAllProductByCategory(String categoryName){
        return productRepository.findAllByCategory_Name(categoryName);
    }
}
