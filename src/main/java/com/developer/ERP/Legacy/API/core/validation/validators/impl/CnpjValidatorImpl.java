package com.developer.ERP.Legacy.API.core.validation.validators.impl;

import com.developer.ERP.Legacy.API.core.annotations.Cnpj;
import com.developer.ERP.Legacy.API.core.validation.validators.documentos.CpfAndCnpjUtils;
import com.developer.ERP.Legacy.API.domain.exceptions.runtime.InvalidCnpjException;
import com.developer.ERP.Legacy.API.domain.exceptions.runtime.InvalidCpfException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CnpjValidatorImpl implements ConstraintValidator<Cnpj, String> {
    private static final String REGGEX_PATTERN = "\\d{14}";
    private static final String REGGEX_PATTERN_CNPJ_MASK = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}";
    private static final List<String> LIST_CNPJ_FORMATTED = Arrays.asList(REGGEX_PATTERN, REGGEX_PATTERN_CNPJ_MASK);

    private static final List<Integer> WGHT_FIRST_DIGIT_CNPJ = Arrays.asList(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
    private static final List<Integer> WGHT_SECOND_DIGIT_CNPJ = Arrays.asList(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);

    private static final String DEFAULT_MESSAGE = "CNPJ invalido!";

    public static boolean isCnpj(String input) {
        return LIST_CNPJ_FORMATTED.stream()
                .anyMatch(regex -> Pattern.matches(regex, input));
    }

    public static boolean isValidCnpj(String input) {
        return isCnpj(input) && new CpfValidatorImpl(input).isValid();
    }

    private String cnpj;

    private List<Integer> cnpjList;


    public CnpjValidatorImpl(){}

    public CnpjValidatorImpl(List<Integer> cnpjList, String cnpj){

        this.cnpjList = cnpjList;
        this.cnpj = cnpj;
    }

    public CnpjValidatorImpl(String input) {
        if (!isCnpj(input)) {
            throw new InvalidCnpjException(input);
        }

        cnpj = input.replaceAll("[^\\d]", "");
        cnpjList = cnpj.chars()
                .map(CpfAndCnpjUtils::charToInt)
                .boxed().collect(Collectors.toList());
    }

    public String getCheckDigits() {
        return cnpj.substring(12);
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCnpjFormatted() {
        return cnpj.substring(0, 2) +
                "." + cnpj.substring(2, 5) +
                "." + cnpj.substring(5, 8) +
                "/" + cnpj.substring(8, 12) +
                "-" + cnpj.substring(12);
    }

    public boolean isValid() {
        String expectedCheckDigits = getFirstDigitCnpj() + getSecondDigitCnpj();
        return expectedCheckDigits.equals(getCheckDigits());
    }

    private String getFirstDigitCnpj() {
        int sum = CpfAndCnpjUtils.sumWgth(cnpjList.subList(0, 12), WGHT_FIRST_DIGIT_CNPJ);
        int expectedFirstDigit = 11 - (sum % 11);
        return Integer.toString(expectedFirstDigit >= 10 ? 0 : expectedFirstDigit);
    }

    private String getSecondDigitCnpj() {
        int sum = CpfAndCnpjUtils.sumWgth(cnpjList.subList(0, 13), WGHT_SECOND_DIGIT_CNPJ);
        int expectedSecondDigit = 11 - (sum % 11);
        return Integer.toString(expectedSecondDigit >= 10 ? 0 : expectedSecondDigit);
    }

    @Override
    public String toString() {
        return getCnpj();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CnpjValidatorImpl cnpj = (CnpjValidatorImpl) o;
        return this.cnpj.equals(cnpj.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

    @Override
    public void initialize(Cnpj constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            return value == null || value.isEmpty() || isCnpj(value) || isValidCnpj(value) ;
        } catch (ConstraintViolationException e) {
            throw new InvalidCnpjException(DEFAULT_MESSAGE + e);
        }
    }
}