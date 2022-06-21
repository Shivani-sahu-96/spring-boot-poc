package com.spring.boot.jpa.spare.part.example.Entity;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Builder
@Table(name = "spare_part")
public class SpareParts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sparepart_id;

    @Column(name="sparepart_name")
    private String sparePartName;

    @Column(name = "sparepart_category")
    private String sparePartCategory;

    @Column(name = "sparepart_price")
    private Double sparePartPrice;

    public SpareParts() {
    }

    public SpareParts(int sparePart_id, String sparePartName, String sparePartCategory, Double sparePartPrice) {
        this.sparepart_id = sparepart_id;
        this.sparePartName = sparePartName;
        this.sparePartCategory = sparePartCategory;
        this.sparePartPrice = sparePartPrice;
    }

    public SpareParts(String sparePartName, Double sparePartPrice) {
        this.sparePartName = sparePartName;
        this.sparePartPrice = sparePartPrice;
    }
}
