/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author 0809379
 */

import database.ProductDA;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Product implements Serializable {

    private String code;
    private String description;
    private double price;

    public Product() {
        code = "";
        description = "";
        price = 0;
    }
    
    public void add() {
        ProductDA.add(this);
    }
    
    public static Product find(String code){
        return ProductDA.find(code);
    }
    
    public static ArrayList<Product> getProducts() {
        return ProductDA.getProducts();
    }
    
    public static void init() {
        ProductDA.init();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
}

