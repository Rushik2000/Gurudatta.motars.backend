package com.gurudatta.motars.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("Supplier")
public class Supplier {
    @Id
    private String sid;
    private String name;
    private String phone;
    private String email;
    private String address;
    private List<String> previousBills;
    private String billId;

}
