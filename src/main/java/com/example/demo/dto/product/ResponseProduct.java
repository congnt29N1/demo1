package com.example.demo.dto.product;

import com.example.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProduct {

    private String productCode;
    private String productName;

    public ResponseProduct(Product product) {
        this.productCode = product.getProductCode();
        this.productName = product.getProductName();
    }
}
