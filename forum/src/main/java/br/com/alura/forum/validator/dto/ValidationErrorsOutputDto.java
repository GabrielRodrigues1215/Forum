/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.forum.validator.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ValidationErrorsOutputDto {

    private List<String> globalErrorMessages = new ArrayList<>();
    private List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();

    public void addFieldError(String field, String message) {
        FieldErrorOutputDto fieldError
                = new FieldErrorOutputDto(field, message);
        fieldErrors.add(fieldError);
    }

    public void addError(String message) {
        globalErrorMessages.add(message);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FieldErrorOutputDto> getErrors() {
        return fieldErrors;
    }

    public int getNumberOfErrors() {
        return this.fieldErrors.size()
                + this.globalErrorMessages.size();
    }

}
