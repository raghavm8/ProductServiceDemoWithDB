package org.raghav.productservicewithdb.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseEntity {
    private String Name;

    @OneToMany(mappedBy = "category")
    List<Product> products;
}
