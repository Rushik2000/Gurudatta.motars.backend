package com.gurudatta.motars.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("Customers")
public class Customer {
    @Id
    private String csid;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String date;
    private String billBy;
    private List<String> previousBills;
    private String billProductId;
}
