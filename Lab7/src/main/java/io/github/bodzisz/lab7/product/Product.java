package io.github.bodzisz.lab7.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends RepresentationModel<Product> {

    public static int idIterator = 1;
    private int id;
    private String name;
    private double price;
    private int quantity;
    private boolean inSale;
}
