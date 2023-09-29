package com.example.demo.service;

import com.example.demo.dto.product.RequestProduct;
import com.example.demo.dto.product.ResponseProduct;
import com.example.demo.dto.product.UpdateRequestProduct;

import java.util.List;

public interface IProductSevice {

    ResponseProduct addProduct(RequestProduct request);

    ResponseProduct updateProduct(Long id, RequestProduct request);

    void deleteProduct(Long id);

    List<ResponseProduct> getProducts();

    ResponseProduct getProductById(Long id);

}
