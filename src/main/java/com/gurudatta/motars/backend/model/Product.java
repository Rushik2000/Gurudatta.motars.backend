package com.gurudatta.motars.backend.model;

import lombok.Data;

@Data
public class Product {
    private String pid;
    private String name;
    private int price;
    private int quantity;
}
