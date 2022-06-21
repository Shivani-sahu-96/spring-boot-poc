package com.spring.boot.jpa.spare.part.example.Repository;

import com.spring.boot.jpa.spare.part.example.Entity.SpareParts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SparePartRepository extends JpaRepository<SpareParts,Integer> {

    List<SpareParts> findBysparePartCategory(String category);

}
