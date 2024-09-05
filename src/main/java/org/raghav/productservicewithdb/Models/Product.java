package org.raghav.productservicewithdb.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseEntity{
    private String Name    ;
    private String Description;
    private double Price;
    @ManyToOne
    private Category category;
}
