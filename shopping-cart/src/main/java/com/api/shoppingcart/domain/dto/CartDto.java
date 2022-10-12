package com.api.shoppingcart.domain.dto;

import com.api.shoppingcart.domain.entities.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CartDto {
    public String id;
    public String documentNumber;
    public List<ItemDto> items;
    public BigDecimal totalPrice;
    public BigDecimal totalPriceWithDiscount;
    public void CalculateTotalPriceWithDiscount(){
        if(this.items == null){
            this.totalPriceWithDiscount = BigDecimal.valueOf(0);
        }
        else if(this.items.stream().count() <= 2){
            this.totalPriceWithDiscount = this.items
                    .stream()
                    .map(c -> c.price)
                    .reduce(BigDecimal.valueOf(0), BigDecimal::add);
        }
        else{
            BigDecimal totalPrice = this.items
                    .stream()
                    .map(c -> c.price)
                    .reduce(BigDecimal.valueOf(0), BigDecimal::add);
            long totalNumberOfDiscounts = this.items.stream().count() / 3;
            Map<BigDecimal, Long> differentPricesUnsorted = this.items
                    .stream()
                    .collect(Collectors.groupingBy(e -> e.price, Collectors.counting()));
            TreeMap<BigDecimal, Long> maximumQuantityOfDiscountsPerPrice = new TreeMap<>(differentPricesUnsorted);
            BigDecimal totalDiscount = BigDecimal.valueOf(0);
            long remaingNumberOfDiscounts = totalNumberOfDiscounts;
            for (Map.Entry<BigDecimal, Long> discount : maximumQuantityOfDiscountsPerPrice.entrySet()) {
                long quantityOfDiscountsForGivenPrice =
                        discount.getValue() <= remaingNumberOfDiscounts ? discount.getValue() : remaingNumberOfDiscounts;
                totalPrice = totalPrice.subtract(
                        discount.getKey().multiply(
                                BigDecimal.valueOf(quantityOfDiscountsForGivenPrice)));
                remaingNumberOfDiscounts = remaingNumberOfDiscounts - discount.getValue();
                if(remaingNumberOfDiscounts <= 0){
                    break;
                }
            }
            this.totalPriceWithDiscount = totalPrice;
        }
    }

    public void CalculateTotalPrice(){
        if(this.items != null){
            this.totalPrice = this.items
                    .stream()
                    .map(c -> c.price)
                    .reduce(BigDecimal.valueOf(0), BigDecimal::add);
        }
        else {
            this.totalPrice = BigDecimal.valueOf(0);
        }
    }
}
