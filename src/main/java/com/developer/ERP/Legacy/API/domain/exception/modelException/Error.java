package com.developer.ERP.Legacy.API.domain.exception.modelException;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class Error {
    @Setter
    private String message;

    @Setter
    private Throwable detail;

    @Setter
    private Object status;

    @Setter
    private Integer code;

    @Setter
    private String errorType;

    private final LocalDate date = LocalDate.now();

    private final LocalTime time = LocalTime.now();
}