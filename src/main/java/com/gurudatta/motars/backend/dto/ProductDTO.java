package com.gurudatta.motars.backend.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ProductDTO {
    @Id
    private String pid;
    private String name;
    private int price;
    private int quantity;
}
