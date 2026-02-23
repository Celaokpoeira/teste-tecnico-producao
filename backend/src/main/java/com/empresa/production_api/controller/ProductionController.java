package com.empresa.production_api.controller;

import com.empresa.production_api.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/production")
@CrossOrigin(origins = "*")
public class ProductionController {

    @Autowired
    private ProductionService productionService;

    @GetMapping("/calculate")
    public Map<String, Object> calculate() {
        return productionService.calculateMaxProfit();
    }
}