package org.raghav.productservicewithdb.Repositories;

import org.raghav.productservicewithdb.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
