/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.forum.validator.dto;

/**
 *
 * @author Gabriel
 */
public class FieldErrorOutputDto {

    private String field;
    private String message;

    public FieldErrorOutputDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
    
    

}
