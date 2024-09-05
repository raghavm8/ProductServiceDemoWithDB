package org.raghav.productservicewithdb.Services;

import jakarta.persistence.EntityNotFoundException;
import org.raghav.productservicewithdb.Models.Category;
import org.raghav.productservicewithdb.Models.Product;
import org.raghav.productservicewithdb.Repositories.CategoryRepository;
import org.raghav.productservicewithdb.Repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
@Primary // to give priority
public class SelfProductService implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();
        if(category.getId() == null) {
            category.setName(product.getName() + " It is category.");
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public String replaceProduct(long id, Product product) {
        return "";
    }

    @Override
    public void deleteProduct(long id) {

    }

    @Override
    public Product updateProduct(long id, Product product) {
        return null;
    }
}
