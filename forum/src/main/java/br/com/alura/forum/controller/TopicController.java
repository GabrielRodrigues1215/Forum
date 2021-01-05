package br.com.alura.forum.controller;

import br.com.alura.forum.dto.input.NewTopicInputDto;
import br.com.alura.forum.dto.input.TopicSearchInputDto;
import br.com.alura.forum.dto.output.DashboardOutputDto;
import br.com.alura.forum.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.dto.output.TopicOutputDto;
import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.DashbordRepository;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.validator.dto.NewTopicCustomValidator;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private DashbordRepository dashbordRepository;

    @InitBinder("newTopicInputDto")
    public void initBinder(WebDataBinder binder, @AuthenticationPrincipal User loggedUser){
        binder.addValidators(new NewTopicCustomValidator(this.topicRepository, loggedUser));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<TopicBriefOutputDto> listTopics(TopicSearchInputDto topicSearch,
            @PageableDefault(sort = "creationInstant", direction = Sort.Direction.DESC) Pageable pageRequest) {

        //List<Topic> topics = topicRepository.list();
        Specification<Topic> topicSearchSpecification = topicSearch.build();
        Page<Topic> topics = this.topicRepository.findAll(topicSearchSpecification,
                pageRequest);

        return TopicBriefOutputDto.listFromTopics(topics);
    }

    @GetMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DashboardOutputDto> dashboards() {

        List<Category> principalCategorys = dashbordRepository.listCategory();

        List<DashboardOutputDto> dto = principalCategorys.stream().map(category -> {
            List<String> nameSubcategory = category.getSubcategoryNames();
            List<Topic> topicSubcategory = topicRepository.findByCourseCategoryName(nameSubcategory);

            return new DashboardOutputDto(category, topicSubcategory);
        }).collect(Collectors.toList());

        return dto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicOutputDto> createTopic(@Valid @RequestBody NewTopicInputDto newTopicDto,
            @AuthenticationPrincipal User loggedUser, UriComponentsBuilder uriBuilder) {

        Topic topic = newTopicDto.build(loggedUser, this.courseRepository);
        this.topicRepository.save(topic);

        URI path = uriBuilder.path("/api/topics/{id}")
                .buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(path).body(new TopicOutputDto(topic));
    }
}
