package com.example.dynamicquery.dao;


import com.example.dynamicquery.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CityDAO extends JpaRepository<City,Long>, JpaSpecificationExecutor<City> {
}
