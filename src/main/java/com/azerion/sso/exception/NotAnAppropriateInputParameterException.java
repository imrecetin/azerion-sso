package com.azerion.sso.exception;

public class NotAnAppropriateInputParameterException extends RuntimeException {
    String fieldName;
    public NotAnAppropriateInputParameterException(String fieldName) {
        super(fieldName+" alanı için uygun değerleri girmelisiniz");
        this.fieldName=fieldName;
    }
    public String getFieldName(){
        return this.fieldName;
    }
}
