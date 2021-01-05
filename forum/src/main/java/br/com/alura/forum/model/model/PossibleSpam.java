/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.forum.model.model;

import br.com.alura.forum.model.topic.domain.Topic;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class PossibleSpam {

    private List<Topic> topics;

    public PossibleSpam(List<Topic> topics) {
        this.topics = topics;
    }

    public boolean hasTopicLimitExceeded() {
        return this.topics.size() >= 4;
    }

    public long minutesToNextTopic(Instant from) {
        Instant instantOfTheOldestTopic = topics.get(0).getCreationInstant();
        return Duration.between(from, instantOfTheOldestTopic)
                .getSeconds() / 60;
    }

}
