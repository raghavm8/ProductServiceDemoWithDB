package org.raghav.productservicewithdb.Services;

import org.raghav.productservicewithdb.Models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    String replaceProduct(long id, Product product);
    void deleteProduct(long id);
    Product updateProduct(long id, Product product);
}