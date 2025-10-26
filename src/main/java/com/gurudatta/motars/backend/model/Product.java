package com.gurudatta.motars.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {
    @Id
    private String pid;
    private String name;
    private int price;
    private int quantity;
}
