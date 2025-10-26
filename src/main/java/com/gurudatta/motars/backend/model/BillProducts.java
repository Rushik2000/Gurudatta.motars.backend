package com.gurudatta.motars.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document("BillProducts")
public class BillProducts {
    @Id
    private String bpid;
    private List<Product> productList;
    private int subtotal;
    private BigDecimal total;
    private BigDecimal tax;
}
