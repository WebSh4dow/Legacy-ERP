package com.developer.ERP.Legacy.API.core.validation.validators.impl;

import com.developer.ERP.Legacy.API.core.validation.validators.cpf.CpfAndCnpjUtils;
import com.developer.ERP.Legacy.API.core.annotations.Cpf;
import com.developer.ERP.Legacy.API.domain.exceptions.runtime.InvalidCpfException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class CpfValidatorImpl implements ConstraintValidator<Cpf, String> {

    private static final String REGEX_PATTERN = "\\d{11}";
    private static final String REGEX_FORMATED = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
    private static final List<String> LIST_CPF_REGEX = Arrays.asList(REGEX_PATTERN,REGEX_FORMATED);
    private static final List<Integer> CHECK_FIRST_DIGIT = Arrays.asList(10,9,8,7,6,5,4,3,2);
    private static final List<Integer> CHECK_SECOND_DIGIT = Arrays.asList(11,10,9,8,7,6,5,4,3);
    private static final String DEFAULT_MESSAGE = "CPF invalido";
    private  List<Integer> cpfAsIntegerList;
    private  String cpf;

    public CpfValidatorImpl(String in) throws InvalidCpfException {
        if (!isCpf(in)){
            throw new InvalidCpfException(in);
        }
        cpf = in.replaceAll("[^\\d]","");
        cpfAsIntegerList = cpf.chars()
                .map(CpfAndCnpjUtils::charToInt)
                .boxed().collect(Collectors.toList());
    }
    public CpfValidatorImpl(){}

    public CpfValidatorImpl(List<Integer> cpfAsIntegerList, String cpf){

        this.cpfAsIntegerList = cpfAsIntegerList;
        this.cpf = cpf;
    }

    @Override
    public void initialize(Cpf constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            return value == null || value.isEmpty() || isCpf(value) || isValidCpf(value) ;
        } catch (ConstraintViolationException e) {
            throw new InvalidCpfException(DEFAULT_MESSAGE + e);
        }
    }

    public static boolean isCpf(String in){
        return LIST_CPF_REGEX.stream()
                .anyMatch(regex -> Pattern.matches(regex,in));
    }
    public static boolean isValidCpf(String in) throws InvalidCpfException {
        return isCpf(in) && new CpfValidatorImpl(in).isValid();
    }

    public String getFirstCheckDigitCpf(){
        int sum = CpfAndCnpjUtils.sumWgth(cpfAsIntegerList.subList(0,9),CHECK_FIRST_DIGIT);
        int expectedFirstCheckDigitCpf = 11 - (sum % 11);
        return Integer.toString(expectedFirstCheckDigitCpf >= 10 ? 0 : expectedFirstCheckDigitCpf);
    }
    public String getSecondCheckDigitCpf(){
        int sum = CpfAndCnpjUtils.sumWgth(cpfAsIntegerList.subList(0,10),CHECK_SECOND_DIGIT);
        int expectedSecondDigitCpf = 11 - (sum % 11);
        return Integer.toString(expectedSecondDigitCpf >= 10 ? 0 : expectedSecondDigitCpf);
    }
    public boolean isValid(){
        String actualDigits = getFirstCheckDigitCpf() + getSecondCheckDigitCpf();
        return actualDigits.equals(getCheckDigits());
    }
    private String getCheckDigits() {
        return cpf.substring(9);
    }
    private String getCpfFormated(){
        return cpf.substring(0,3) +
                "." + cpf.substring(3,6) +
                "." + cpf.substring(6,9) +
                "." + cpf.substring(9);
    }

    public String getCpf() {
        return cpf;
    }
    @Override
    public String toString() {
        return getCpf();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CpfValidatorImpl cpfValidator = (CpfValidatorImpl) o;
        return cpf.equals(cpfValidator.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

}
