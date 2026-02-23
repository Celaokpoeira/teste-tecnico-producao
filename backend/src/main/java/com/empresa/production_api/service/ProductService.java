package com.empresa.production_api.service;

import com.empresa.production_api.model.Product;
import com.empresa.production_api.model.RawMaterial;
import com.empresa.production_api.repository.ProductRepository;
import com.empresa.production_api.repository.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductionService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    public Map<String, Object> calculateMaxProfit() {
        List<Product> products = productRepository.findAll();
        List<RawMaterial> materials = rawMaterialRepository.findAll();
        
        // Mapa para controlar o estoque atual de cada material
        Map<Long, Double> currentStock = new HashMap<>();
        for (RawMaterial m : materials) {
            currentStock.put(m.getId(), m.getStockQuantity());
        }

        // Variáveis para guardar o melhor resultado encontrado
        Map<String, Integer> bestProduction = new HashMap<>();
        final double[] maxProfit = {0.0};

        // Iniciamos a busca recursiva
        findBestCombination(products, currentStock, new HashMap<>(), 0.0, maxProfit, bestProduction);

        Map<String, Object> response = new HashMap<>();
        // Correção: Map usa .put() em vez de .add()
        response.put("suggested_production", bestProduction);
        response.put("total_value", maxProfit[0]);
        return response;
    }

    private void findBestCombination(List<Product> products, Map<Long, Double> stock, 
                                     Map<String, Integer> currentProduction, double currentValue, 
                                     double[] maxProfit, Map<String, Integer> bestProduction) {
        
        if (currentValue > maxProfit[0]) {
            maxProfit[0] = currentValue;
            bestProduction.clear();
            bestProduction.putAll(currentProduction);
        }

        for (Product p : products) {
            if (canManufacture(p, stock)) {
                // Tenta fabricar 1 unidade
                consumeMaterials(p, stock);
                currentProduction.put(p.getName(), currentProduction.getOrDefault(p.getName(), 0) + 1);
                
                findBestCombination(products, stock, currentProduction, currentValue + p.getValue(), maxProfit, bestProduction);
                
                // Backtrack: "desfaz" a fabricação para tentar outro caminho
                currentProduction.put(p.getName(), currentProduction.get(p.getName()) - 1);
                restoreMaterials(p, stock);
            }
        }
    }

    private boolean canManufacture(Product p, Map<Long, Double> stock) {
        if (p.getCompositions() == null) return false;
        return p.getCompositions().stream().allMatch(c -> 
            stock.get(c.getRawMaterial().getId()) >= c.getQuantityNeeded());
    }

    private void consumeMaterials(Product p, Map<Long, Double> stock) {
        p.getCompositions().forEach(c -> 
            stock.put(c.getRawMaterial().getId(), stock.get(c.getRawMaterial().getId()) - c.getQuantityNeeded()));
    }

    private void restoreMaterials(Product p, Map<Long, Double> stock) {
        p.getCompositions().forEach(c -> 
            stock.put(c.getRawMaterial().getId(), stock.get(c.getRawMaterial().getId()) + c.getQuantityNeeded()));
    }
}