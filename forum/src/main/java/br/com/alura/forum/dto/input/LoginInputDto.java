/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.forum.dto.input;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author Gabriel
 */
public class LoginInputDto {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken build() {
        return new UsernamePasswordAuthenticationToken(this.email, this.password);
    }
}
