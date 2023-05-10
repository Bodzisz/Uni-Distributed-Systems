package io.github.bodzisz.lab7;

import io.github.bodzisz.lab7.exception.NegativeValueException;
import io.github.bodzisz.lab7.product.Product;
import io.github.bodzisz.lab7.product.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final ProductService productService;

    public DataInitializer(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void initData() throws NegativeValueException {
        if(productService.getProducts().isEmpty()) {
            productService.addProduct(new Product(0, "Lego", 450.99, 2, true));
            productService.addProduct(new Product(0, "Polo shirt", 120.23, 10, true));
            productService.addProduct(new Product(0, "Nvidia RTX 4090", 6650.34, 1, false));
        }
    }
}
