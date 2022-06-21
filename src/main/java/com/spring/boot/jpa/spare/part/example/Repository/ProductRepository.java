package com.spring.boot.jpa.spare.part.example.Repository;

import com.spring.boot.jpa.spare.part.example.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCompanyName(String category);

}
