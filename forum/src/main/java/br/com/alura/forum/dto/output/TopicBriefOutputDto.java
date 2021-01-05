/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.forum.dto.output;

import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.model.topic.domain.TopicStatus;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

/**
 *
 * @author Gabriel
 */
public class TopicBriefOutputDto {

    private Long id;
    private String shortDescription;
    private long secondsSinceLastUpdate;
    private String ownerName;
    private String courseName;
    private String subcategoryName;
    private String categoryName;
    private int numberOfResponses;

    public Long getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public long getSecondsSinceLastUpdate() {
        return secondsSinceLastUpdate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getNumberOfResponses() {
        return numberOfResponses;
    }

    public boolean isSolved() {
        return solved;
    }
    private boolean solved;

    public TopicBriefOutputDto(Topic topic) {
        this.id = topic.getId();
        this.shortDescription = topic.getShortDescription();
        this.secondsSinceLastUpdate = getSecondsSince(topic.getLastUpdate());
        this.ownerName = topic.getOwner().getName();
        this.courseName = topic.getCourse().getName();
        this.subcategoryName = topic.getCourse().getSubcategory().getName();
        this.categoryName = topic.getCourse().getCategoryName();
        this.numberOfResponses = topic.getNumberOfAnswers();
        this.solved = TopicStatus.SOLVED.equals(topic.getStatus());
    }

    private long getSecondsSince(Instant lastUpdate) {
        return Duration.between(lastUpdate, Instant.now())
                .get(ChronoUnit.SECONDS);
    }

    public static Page<TopicBriefOutputDto> listFromTopics(Page<Topic> topicPage) {
        return topicPage.map(TopicBriefOutputDto::new);
    }

}
