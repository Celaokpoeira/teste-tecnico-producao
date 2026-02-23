package com.empresa.production_api.service;

import com.empresa.production_api.model.RawMaterial;
import com.empresa.production_api.repository.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RawMaterialService {

    @Autowired
    private RawMaterialRepository repository;

    public List<RawMaterial> findAll() {
        return repository.findAll();
    }

    public RawMaterial save(RawMaterial rawMaterial) {
        return repository.save(rawMaterial);
    }

    public RawMaterial findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Material not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}