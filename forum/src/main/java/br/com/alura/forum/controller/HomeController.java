/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Gabriel
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    
    
    @ResponseBody
    @RequestMapping(value = "/v1")
    public String index(){
        System.out.println("Log	do servidor de que foi feita uma requisição para '/'.");
        return "Bem vindo ao forum da Alura";
    }
}
