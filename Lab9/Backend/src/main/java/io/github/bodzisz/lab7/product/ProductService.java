package io.github.bodzisz.lab7.product;

import io.github.bodzisz.lab7.exception.ExceededSaleException;
import io.github.bodzisz.lab7.exception.NegativeValueException;
import io.github.bodzisz.lab7.exception.ProductNotFoundException;
import io.github.bodzisz.lab7.exception.ProductNotInSaleException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final Map<Integer, Product> products = new HashMap<>();

    public List<Product> getProducts() {
        return products.values().stream().toList();
    }

    public Product getProduct(final int id) throws ProductNotFoundException {
        Product product = products.get(id);

        if(product == null) {
            throw new ProductNotFoundException(id);
        }

        return product;
    }

    public Product addProduct(final Product product) throws NegativeValueException {
        validateProduct(product);
        product.setId(Product.idIterator++);
        products.put(product.getId(), product);
        return product;
    }

    public Product updateProduct(final Product product) throws ProductNotFoundException, NegativeValueException {
        Product toUpdate = getProduct(product.getId());

        validateProduct(product);

        toUpdate.setName(product.getName());
        toUpdate.setPrice(product.getPrice());
        toUpdate.setQuantity(product.getQuantity());
        toUpdate.setInSale(product.isInSale());

        return toUpdate;
    }

    public Product deleteProduct(final int id) throws ProductNotFoundException {
        Product toDelete = getProduct(id);

        products.remove(id);

        return toDelete;
    }

    public Product sellProduct(final int id, final int quantity) throws ProductNotFoundException, NegativeValueException, ExceededSaleException, ProductNotInSaleException {
        Product product = getProduct(id);

        if(!product.isInSale()) {
            throw new ProductNotInSaleException(product.getName());
        }

        if(quantity < 0) {
            throw new NegativeValueException("sell quantity");
        }

        if(product.getQuantity() - quantity < 0) {
            throw new ExceededSaleException(product.getQuantity(), quantity, product.getName());
        }

        product.setQuantity(product.getQuantity() - quantity);

        return product;
    }

    public Product addToProductQuantity(final int id, final int quantity) throws ProductNotFoundException, NegativeValueException {
        Product product = getProduct(id);

        if(quantity < 0) {
            throw new NegativeValueException("sell quantity");
        }

        product.setQuantity(product.getQuantity() + quantity);
        return product;
    }

    private void validateProduct(final Product product) throws NegativeValueException {
        if(product.getPrice() < 0) {
            throw new NegativeValueException("price");
        }
        if(product.getQuantity() < 0) {
            throw new NegativeValueException("quantity");
        }
    }

}
