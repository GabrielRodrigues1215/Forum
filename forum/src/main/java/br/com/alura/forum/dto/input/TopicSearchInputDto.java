/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.forum.dto.input;

import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.model.topic.domain.TopicStatus;
import java.util.ArrayList;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Gabriel
 */
public class TopicSearchInputDto {

    private TopicStatus status;
    private String categoryName;

    public TopicStatus getStatus() {
        return status;
    }

    public void setStatus(TopicStatus status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // MODO DE RESOLVER COM UMA EXPRESSÃO LAMBIDA

        public Specification<Topic> build() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            if (categoryName != null) {
                Path<String> categoryNamePath = root.get("course").get("subcategory")
                        .get("category").get("name");
                predicates.add(criteriaBuilder.equal(categoryNamePath, categoryName));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
// MODO DE RESOLVER COM UMA CLASSE   
//        public class TopicSearchSpecificationImpl implements Specification<Topic> {
//
//    @Override
//    public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        ArrayList<Predicate> predicates = new ArrayList<>();
//
//        if (status != null) {
//            predicates.add(criteriaBuilder.equal(root.get("status"), status));
//        }
//
//        if (categoryName != null) {
//            Path<String> categoryNamePath = root.get("course").get("subcategory")
//                    .get("category").get("name");
//            predicates.add(criteriaBuilder.equal(categoryNamePath, categoryName));
//        }
//
//        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//    }
//}

      // MODO DE RESOLVER COM UMA uma inner class
   
  
//            class TopicSearchSpecificationImpl implements Specification<Topic> {
//
//        @Override
//        public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> query, 
//		CriteriaBuilder criteriaBuilder) {
//            ArrayList<Predicate> predicates = new ArrayList<>();
//
//            if (status != null) {
//                predicates.add(criteriaBuilder.equal(root.get("status"), status));
//            }
//
//            if (categoryName != null) {
//                Path<String> categoryNamePath = root.get("course").get("subcategory")
//                        .get("category").get("name");
//                predicates.add(criteriaBuilder.equal(categoryNamePath, categoryName));
//            }
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        }
//    }
       
        // MODO DE RESOLVER COM uma classe anônima


//            public Specification<Topic> build() {
//        return new Specification<Topic>() {
//            @Override
//            public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> query, 
//	    	CriteriaBuilder criteriaBuilder) {
//                ArrayList<Predicate> predicates = new ArrayList<>();
//
//                if (status != null) {
//                    predicates.add(criteriaBuilder.equal(root.get("status"), status));
//                }
//
//                if (categoryName != null) {
//                    Path<String> categoryNamePath = root.get("course").get("subcategory")
//                            .get("category").get("name");
//                    predicates.add(criteriaBuilder.equal(categoryNamePath, categoryName));
//                }
//
//                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//            }
//        };
//    }
}
