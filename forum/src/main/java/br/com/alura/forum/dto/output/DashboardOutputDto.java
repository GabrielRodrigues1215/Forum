/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.forum.dto.output;

import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.topic.domain.Topic;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

/**
 *
 * @author Gabriel
 */
public class DashboardOutputDto {


    private String categoryName;
    private List<String> subcategories;
    private int allTopics;
    private Long lastWeekTopics;
    private Long unansweredTopics;

    public DashboardOutputDto(Category category, List<Topic> topics) {
        this.categoryName = category.getName();
        this.subcategories = category.getSubcategoryNames();
        this.allTopics = topics.size();
        this.lastWeekTopics = topics.stream().filter(topic -> topic.isOneWeekOld()).count();
        this.unansweredTopics = topics.stream().filter(topic -> topic.isUnanswered()).count();
    }


    public String getCategoryName() {
        return categoryName;
    }

    public List<String> getSubcategories() {
        return subcategories;
    }

    public int getAllTopics() {
        return allTopics;
    }

    public Long getLastWeekTopics() {
        return lastWeekTopics;
    }

    public Long getUnansweredTopics() {
        return unansweredTopics;
    }

}
