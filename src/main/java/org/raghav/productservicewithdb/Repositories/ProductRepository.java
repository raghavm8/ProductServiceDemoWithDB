package org.raghav.productservicewithdb.Repositories;

import org.raghav.productservicewithdb.Models.Product;
import org.raghav.productservicewithdb.Projections.ProductWithNameAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    void delete(Product product);
    @Override
    Product save(Product product);
    @Override
    Optional<Product> findById(Long id);

    // HQL
    @Query("select p.Name as Name, p.Description as Description from Product p where p.id =: id")
    ProductWithNameAndDescription findNameAndDescriptionHQL(@Param("id") Long id);

    // SQL
    @Query(value = "select Name, Description from product where id =: id", nativeQuery = true)
    ProductWithNameAndDescription findNameAndDescriptionSQL(@Param("id") Long id);
}
