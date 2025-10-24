package com.gurudatta.motars.backend.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Admin")
public class Admin {
    private String aid;
    private String name;
}
