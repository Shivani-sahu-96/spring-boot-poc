package com.spring.boot.jpa.spare.part.example.Entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "product")
public class Product  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_no", unique = true)
    int model_no;
    @Column(name = "company_name")
    String companyName;
    @Column(name = "price")
    Double price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "model_no", referencedColumnName = "model_no")
    private List<SpareParts> spareParts;


    public Product() {
    }

    public Product(int model_no, String companyName, Double price, List<SpareParts> spareParts) {
        this.model_no = model_no;
        this.companyName = companyName;
        this.price = price;
        this.spareParts = spareParts;
    }

    public Product(int id, String companyName, double price) {
        this.model_no = model_no;
        this.companyName = companyName;
        this.price = price;
    }
}

