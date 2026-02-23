package com.empresa.production_api;

import com.empresa.production_api.model.Product;
import com.empresa.production_api.model.ProductComposition;
import com.empresa.production_api.model.RawMaterial;
import com.empresa.production_api.repository.ProductRepository;
import com.empresa.production_api.repository.RawMaterialRepository;
import com.empresa.production_api.service.ProductionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith; 
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension; 
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class) // Isso aqui faz o NullPointerException sumir!
public class ProductionServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RawMaterialRepository rawMaterialRepository;

    @InjectMocks
    private ProductionService productionService;

    @Test
    public void testCalculateMaxProfit() {
        RawMaterial aco = new RawMaterial();
        aco.setId(1L);
        aco.setName("Aço");
        aco.setStockQuantity(10.0);

        Product prateleira = new Product();
        prateleira.setName("Prateleira");
        prateleira.setValue(100.0);
        
        ProductComposition comp = new ProductComposition();
        comp.setRawMaterial(aco);
        comp.setQuantityNeeded(5.0);
        prateleira.setCompositions(Collections.singletonList(comp));

        when(productRepository.findAll()).thenReturn(Collections.singletonList(prateleira));
        when(rawMaterialRepository.findAll()).thenReturn(Collections.singletonList(aco));

        Map<String, Object> result = productionService.calculateMaxProfit();

        assertEquals(200.0, result.get("total_value"));
        @SuppressWarnings("unchecked")
        Map<String, Integer> suggested = (Map<String, Integer>) result.get("suggested_production");
        assertEquals(2, suggested.get("Prateleira"));
    }
}