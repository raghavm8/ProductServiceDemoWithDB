package org.raghav.productservicewithdb.Exceptions;

public class ProductLimitReachedException extends Exception {
    public ProductLimitReachedException(String limitIsReached) {
        super(limitIsReached);
    }
}

