package com.techtitans.ecommerce.utils;

public final class VerificationUtils {

    public static Boolean isMissing(String string){
        Boolean missing = (string.isEmpty() || string.isBlank() || string.length()<=0 || string.equals("") || string.equals(null));
        return missing;
    }
}
