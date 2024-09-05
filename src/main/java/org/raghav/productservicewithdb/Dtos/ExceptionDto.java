package org.raghav.productservicewithdb.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDto {
    private String message;
    private String errorCode;
}

