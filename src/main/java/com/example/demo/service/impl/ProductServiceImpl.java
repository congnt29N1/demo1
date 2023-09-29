package com.example.demo.service.impl;

import com.example.demo.dto.product.RequestProduct;
import com.example.demo.dto.product.ResponseProduct;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.IProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductSevice {
    @Autowired
    private ProductRepository repository;


    @Override
    public ResponseProduct addProduct(RequestProduct request) {
        Product product = new Product();
        product.setProductCode(request.getProductCode());
        product.setProductName(request.getProductName());
        Product product2 = repository.save(product);
        return new ResponseProduct(product2);
    }

    @Override
    public ResponseProduct updateProduct(Long id, RequestProduct request) {
        Product product = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
        product.setProductCode(request.getProductCode());
        product.setProductName(request.getProductName());
        Product product2 = repository.save(product);
        return new ResponseProduct(product2);
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);

    }

    @Override
    public List<ResponseProduct> getProducts() {
        List<Product> productList = repository.findAll();
        List<ResponseProduct> responseProducts = new ArrayList<>();
        productList.stream().forEach(product -> responseProducts.add(new ResponseProduct(product)));
        return responseProducts;
    }

    @Override
    public ResponseProduct getProductById(Long id) {
        Product product = repository.findById(id).orElse(null);
        if(product !=null) {
            return new ResponseProduct(product);
        }
        return null;
    }
}
