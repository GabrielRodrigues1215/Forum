/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.forum.repository;

import br.com.alura.forum.model.Course;
import org.springframework.data.repository.Repository;

/**
 *
 * @author Gabriel
 */
public interface CourseRepository extends Repository<Course, Long>{
    
    Course findByName(String name);
    
}
