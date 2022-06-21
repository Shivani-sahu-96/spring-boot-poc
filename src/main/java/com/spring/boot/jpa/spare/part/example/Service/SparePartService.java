package com.spring.boot.jpa.spare.part.example.Service;

import com.spring.boot.jpa.spare.part.example.Entity.Product;
import com.spring.boot.jpa.spare.part.example.Entity.SpareParts;
import com.spring.boot.jpa.spare.part.example.Exception.ProductNotFoundException;
import com.spring.boot.jpa.spare.part.example.Repository.SparePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SparePartService {

    @Autowired
    private SparePartRepository sparepartRepository;


    public SpareParts saveSpareParts(SpareParts spareParts) {
        return sparepartRepository.save(spareParts);
    }

    public List<SpareParts> getAllSpareParts() {
        return sparepartRepository.findAll();
    }

    public List<SpareParts> getSparePartByCategory(String name) {
        return sparepartRepository.findBysparePartCategory(name);
    }


    public SpareParts getSparePartById(int id) {
        boolean productPresent=sparepartRepository.findById(id).isPresent();

        if (productPresent) {
            SpareParts spareParts = sparepartRepository.findById(id).orElseThrow();
            return spareParts;
        }
        else {
            return null;
        }
    }


    public SpareParts updateSpareParts(SpareParts spareParts, int id) {
        SpareParts updateProduct=sparepartRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Item Not Found : "+id));
        updateProduct.setSparePartName(spareParts.getSparePartName());
        updateProduct.setSparePartPrice(spareParts.getSparePartPrice());
        updateProduct.setSparePartCategory(spareParts.getSparePartCategory());
        sparepartRepository.save(updateProduct);
        return updateProduct;
    }

    public void deleteSpareParts(int id) {
        sparepartRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Item Not Found : "+id));
        sparepartRepository.deleteById(id);
    }
    public boolean checkName(String name) {
        if (getSparePartByCategory(name) == null) {
            return false;
        }
        return true;
    }

}
