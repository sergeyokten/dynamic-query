package com.example.dynamicquery.dao;

import com.example.dynamicquery.models.City;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
public class CitySpecification {

    public static Specification<City> kabulName() {


        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), "Kabul");

    }


}
