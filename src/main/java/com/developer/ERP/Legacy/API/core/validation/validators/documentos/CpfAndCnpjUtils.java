package com.developer.ERP.Legacy.API.core.validation.validators.documentos;

import com.developer.ERP.Legacy.API.core.validation.validators.impl.cpf.CpfValidatorImpl;

import java.util.List;

public class CpfAndCnpjUtils {

    public static int charToInt(int ch){
        return ch - 0;
    }

    public static int sumWgth(List<Integer> digits, List<Integer> weights){
        int sum = 0;
        for (int i = 0; i < digits.size();i++){
            sum += digits.get(i) * weights.get(i);
        }
        return sum;
    }

    public static boolean isCpf(String cpfInput){
        return CpfValidatorImpl.isCpf(cpfInput);
    }

}