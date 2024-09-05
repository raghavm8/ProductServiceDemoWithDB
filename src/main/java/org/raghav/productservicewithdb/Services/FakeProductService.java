package org.raghav.productservicewithdb.Services;

import org.raghav.productservicewithdb.Dtos.FakeStoreProductDto;
import org.raghav.productservicewithdb.Models.Category;
import org.raghav.productservicewithdb.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeProductService")
public class FakeProductService implements ProductService {

    private String apiUrl = "https://fakestoreapi.com/products/";
    private RestTemplate restTemplate;

    public FakeProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(apiUrl + id,
                FakeStoreProductDto.class);
        Product product = this.ConvertToProduct(fakeStoreProductDto);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] products = restTemplate.getForObject(apiUrl, FakeStoreProductDto[].class);
        List<Product> productList = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : products) {
            productList.add(this.ConvertToProduct(fakeStoreProductDto));
        }
        return productList;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = this.ConvertToDto(product);
        FakeStoreProductDto prod = restTemplate.postForObject(apiUrl, fakeStoreProductDto, FakeStoreProductDto.class);
        return ConvertToProduct(prod);
    }

    @Override
    public String replaceProduct(long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = this.ConvertToDto(product);
        restTemplate.put(apiUrl + product.getId(), fakeStoreProductDto);
        return "Product Updated";
    }

    @Override
    public void deleteProduct(long id) {

    }

    @Override
    public Product updateProduct(long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = this.ConvertToDto(product);
        fakeStoreProductDto = restTemplate.patchForObject(apiUrl + id, fakeStoreProductDto, FakeStoreProductDto.class);
        return ConvertToProduct(fakeStoreProductDto);
    }

    private Product ConvertToProduct(FakeStoreProductDto dto) {
        Product product = new Product();

        // Set the ID; if dto.getId() is null, default to 0
        product.setId(dto.getId() != null ? dto.getId() : 0);

        // Map title to Name in Product
        product.setName(dto.getTitle());

        // Map Description
        product.setDescription(dto.getDescription());

        // Map Price
        product.setPrice(dto.getPrice());

        // Convert category string to Category object
        if (dto.getCategory() != null) {
            Category category = new Category();
            category.setId(0L); // Default ID; adjust as necessary
            category.setName(dto.getCategory());
            product.setCategory(category);
        } else {
            product.setCategory(null);
        }

        return product;
    }

    private FakeStoreProductDto ConvertToDto(Product product) {
        FakeStoreProductDto dto = new FakeStoreProductDto();

        // Set the ID
        dto.setId(product.getId());

        // Map Name in Product to title in DTO
        dto.setTitle(product.getName());

        // Map Description
        dto.setDescription(product.getDescription());

        // Map Price
        dto.setPrice(product.getPrice());

        // Convert Category object to category string
        if (product.getCategory() != null) {
            dto.setCategory(product.getCategory().getName());
        } else {
            dto.setCategory(null);
        }

        return dto;
    }
}