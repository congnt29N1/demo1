package com.example.demo.controller;

import com.example.demo.dto.product.RequestProduct;
import com.example.demo.dto.product.ResponseProduct;
import com.example.demo.dto.product.UpdateRequestProduct;
import com.example.demo.service.IProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/token")
public class ProductController {

    @Autowired
    private IProductSevice sevice;


    @PostMapping("/add")
    public ResponseEntity<ResponseProduct> addProduct(@RequestBody RequestProduct requestProduct) {
        ResponseProduct product = sevice.addProduct(requestProduct);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseProduct>> getProduct() {
        List<ResponseProduct> responseProducts = sevice.getProducts();
        return new ResponseEntity<>(responseProducts, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseProduct> delete(@PathVariable Long id) {
        sevice.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<ResponseProduct> put(@PathVariable Long id, @RequestBody RequestProduct request) {
        ResponseProduct updateRequestProduct = sevice.updateProduct(id, request);
        if (updateRequestProduct != null) {
            return new ResponseEntity<>(updateRequestProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseProduct> get(@PathVariable Long id) {
        ResponseProduct responseProduct = sevice.getProductById(id);
        if (responseProduct != null) {
            return new ResponseEntity<>(responseProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
