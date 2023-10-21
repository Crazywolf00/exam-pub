package com.example.exampub.models.dtos;

import com.example.exampub.models.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ProductInfoDTO {

    private String productName;
    private int amount;
    private double unitPrice;
    private double summaryPrice;

    public ProductInfoDTO(Product product) {
        this.productName = product.getProductName();
        this.unitPrice = product.getPrice();
    }

    public void countSummaryPrice() {
        summaryPrice = amount * unitPrice;
    }

    public void increaseAmount(int inputAmount) {
        amount += inputAmount;
    }
}
