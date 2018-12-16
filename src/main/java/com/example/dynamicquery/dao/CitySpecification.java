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
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@Builder
@Data
public class CitySpecification {


    public static Specification<City> kabulName() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), "Kabul");
    }


    public static Specification<City> equalSpecification(CustomCondition... customConditions) {
        List<CustomCondition> conditions = Arrays.asList(customConditions);

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.and(
                        conditions.stream().map(
                                customCondition -> criteriaBuilder.gt(root.get(customCondition.getField()), Integer.parseInt(customCondition.getValue())))
                                .collect(Collectors.toList())
                                .toArray(new Predicate[conditions.size()]));
    }

}
