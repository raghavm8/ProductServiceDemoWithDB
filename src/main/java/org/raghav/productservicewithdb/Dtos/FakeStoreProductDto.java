package org.raghav.productservicewithdb.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title    ;
    private String Description;
    private double Price;
    private String Category;
}

