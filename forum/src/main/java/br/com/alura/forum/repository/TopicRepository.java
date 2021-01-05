package br.com.alura.forum.repository;

import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gabriel
 */
public interface TopicRepository extends Repository<Topic, Long>, JpaSpecificationExecutor<Topic> {

    //JPQL
    @Query("select t from Topic	t")
    List<Topic> list();

    List<Topic> findAll();
    
    @Query("select t from Topic t where t.course.subcategory.name in :subcategoryNames")
    List<Topic> findByCourseCategoryName(@Param("subcategoryNames") List<String> subcategoryNames);

    void save(Topic topic);

    public List<Topic> findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(User loggedUser, Instant oneHourAgo);

}
