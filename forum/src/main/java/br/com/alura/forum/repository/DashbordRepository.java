/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.forum.repository;

import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.topic.domain.Topic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 *
 * @author Gabriel
 */
public interface DashbordRepository extends Repository<Category, Long> {

   
    @Query("select c from Category c where c.subcategories is not empty")
    List<Category> listCategory();
    
}
