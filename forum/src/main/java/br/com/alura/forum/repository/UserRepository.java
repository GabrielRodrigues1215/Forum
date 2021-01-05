/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.forum.repository;

import br.com.alura.forum.model.User;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 *
 * @author Gabriel
 */
public interface UserRepository extends Repository<User, Long> {

    Optional<User> findByEmail(String email);

    public Optional<User> findById(Long userId);
}
