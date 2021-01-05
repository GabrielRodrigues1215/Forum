/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.forum.security.service;

import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gabriel
 */
@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Optional<User> possibleUser = userRepository.findByEmail(username);

        return possibleUser.orElseThrow(()
                -> new UsernameNotFoundException(
                        "Não foi possível "
                        + "encontrar usuário com email: "
                        + username));
    }

    public UserDetails loadUserById(Long userId) {
        Optional<User> possibleUser = userRepository.findById(userId);
        return possibleUser.orElseThrow(
                () -> new UsernameNotFoundException(
        "Não foi possível encontrar o usuário com id: " + userId));   
    }
}
