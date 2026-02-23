package com.empresa.production_api.controller;

import com.empresa.production_api.model.RawMaterial;
import com.empresa.production_api.service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/raw-materials")
@CrossOrigin(origins = "*") // Permite que o Vue.js acesse a API sem erros de CORS
public class RawMaterialController {

    @Autowired
    private RawMaterialService service;

    @GetMapping
    public List<RawMaterial> listAll() {
        return service.findAll();
    }

    @PostMapping
    public RawMaterial create(@RequestBody RawMaterial rawMaterial) {
        return service.save(rawMaterial);
    }
}