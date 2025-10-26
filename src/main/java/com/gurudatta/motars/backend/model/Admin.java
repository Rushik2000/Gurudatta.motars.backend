package com.gurudatta.motars.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Admin")
public class Admin {
    @Id
    private String aid;
    private String name;
}
