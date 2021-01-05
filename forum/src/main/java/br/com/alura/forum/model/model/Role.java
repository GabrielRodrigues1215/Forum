/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.forum.model.model;

import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.Entity;
import org.springframework.security.core.GrantedAuthority;



/**
 *
 * @author Gabriel
 */
@Entity
public class Role implements GrantedAuthority {

    public static final Role ROLE_ADMIN = new Role("ROLE_ADMIN");
    public static final Role ROLE_ALUNO = new Role("ROLE_ALUNO");

    @Id
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    /**
     * @deprecated
     */
    public Role() {
    }

    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(authority, role.authority);
    }

}
