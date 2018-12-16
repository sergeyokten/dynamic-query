package com.example.dynamicquery.dao;

import com.example.dynamicquery.models.City;
import com.example.dynamicquery.models.CustomCondition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CityPredicate implements Specification<City> {


    List<CustomCondition> conditionList = new ArrayList<>();

    public CityPredicate(CustomCondition... conditions) {
        this.conditionList = Arrays.asList(conditions);
    }

    @Override
    public Predicate toPredicate(Root<City> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = buildPredicates(root, criteriaQuery, criteriaBuilder);

        return predicates.size() > 1 ?
                criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]))
                :
                predicates.get(0);
    }

    private List<Predicate> buildPredicates(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        conditionList.forEach(customCondition -> predicates.add(buildPredicate(customCondition, root, criteriaQuery, criteriaBuilder)));

        return predicates;

    }

    private Predicate buildPredicate(CustomCondition condition, Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

        switch (condition.getComparison()) {
            case "eq":
                System.out.println("eq");
                return criteriaBuilder.equal(root.get(condition.getField()), condition.getValue());

            case "gt":
                System.out.println("gt");
                return criteriaBuilder.gt(root.get(condition.getField()), Integer.parseInt(condition.getValue()));

            case "lt":
                System.out.println("lt");
                return criteriaBuilder.lt(root.get(condition.getField()), Integer.parseInt(condition.getValue()));

        }

        return null;

    }

}
