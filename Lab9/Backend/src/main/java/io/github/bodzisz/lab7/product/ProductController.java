package io.github.bodzisz.lab7.product;

import io.github.bodzisz.lab7.exception.ExceededSaleException;
import io.github.bodzisz.lab7.exception.NegativeValueException;
import io.github.bodzisz.lab7.exception.ProductNotFoundException;
import io.github.bodzisz.lab7.exception.ProductNotInSaleException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<Product>> getProducts() throws ProductNotFoundException, NegativeValueException, ProductNotInSaleException, ExceededSaleException {
        final List<Product> productList = productService.getProducts();

        for (Product product : productList) {
            addLinksToProduct(product);
        }

        final Link link = linkTo(methodOn(ProductController.class).getProducts()).withSelfRel();
        return ResponseEntity.ok(CollectionModel.of(productList, link));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable final int id) throws ProductNotFoundException, NegativeValueException, ProductNotInSaleException, ExceededSaleException {
        final Product product = productService.getProduct(id);

        addLinksToProduct(product);

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody final Product product) throws ProductNotFoundException, NegativeValueException, ProductNotInSaleException, ExceededSaleException {
        final Product productToReturn = productService.addProduct(product);

        addLinksToProduct(product);

        return ResponseEntity.status(201).body(productToReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable final int id) throws ProductNotFoundException, NegativeValueException, ProductNotInSaleException, ExceededSaleException {
        final Product productToReturn = productService.deleteProduct(id);

        productToReturn.removeLinks();
        productToReturn.add(linkTo(methodOn(ProductController.class).getProducts()).withRel("list all"));

        return ResponseEntity.ok(productToReturn);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody final Product product) throws ProductNotFoundException, NegativeValueException, ProductNotInSaleException, ExceededSaleException {
        final Product productToReturn = productService.updateProduct(product);

        addLinksToProduct(product);

        return ResponseEntity.ok(productToReturn);
    }

    @GetMapping("/{id}/sell/{quantity}")
    public ResponseEntity<Product> sellProduct(@PathVariable final int id, @PathVariable final int quantity) throws NegativeValueException, ExceededSaleException, ProductNotFoundException, ProductNotInSaleException {
        final Product product = productService.sellProduct(id, quantity);

        addLinksToProduct(product);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}/add/{quantity}")
    public ResponseEntity<Product> addToProductQuantity(@PathVariable final int id, @PathVariable final int quantity) throws NegativeValueException, ProductNotFoundException, ProductNotInSaleException, ExceededSaleException {
        final Product product = productService.addToProductQuantity(id, quantity);

        addLinksToProduct(product);

        return ResponseEntity.ok(product);
    }

    private void addLinksToProduct(final Product product) throws ProductNotFoundException, NegativeValueException, ProductNotInSaleException, ExceededSaleException {
        product.removeLinks();

        product.add(
                linkTo(methodOn(ProductController.class).getProduct(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).deleteProduct(product.getId())).withRel("delete"),
                linkTo(methodOn(ProductController.class).addToProductQuantity(product.getId(), 1)).withRel("add quantity")
        );

        if(product.isInSale() && product.getQuantity() > 0) {
            product.add(
                    linkTo(methodOn(ProductController.class).sellProduct(product.getId(), 1)).withRel("sell")
            );
        }

        product.add(
                linkTo(methodOn(ProductController.class).getProducts()).withRel("list all")
        );
    }

}
