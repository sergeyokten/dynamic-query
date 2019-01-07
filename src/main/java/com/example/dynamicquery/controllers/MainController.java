package com.example.dynamicquery.controllers;

import com.example.dynamicquery.dao.CityDAO;
import com.example.dynamicquery.dao.CityPredicate;
import com.example.dynamicquery.dao.CitySpecification;
import com.example.dynamicquery.models.City;
import com.example.dynamicquery.models.CustomCondition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class MainController {


    CityDAO cityDAO;

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "5") Integer size, Model model) {
        System.out.println(offset + " " + size);
        Pageable pageable = PageRequest.of(offset, size);
        Page<City> cityPage = cityDAO.findAll(CitySpecification.kabulName(), pageable);


        List<City> content = cityPage.getContent();
        System.out.println(content);
        model.addAttribute("cities", content);
        return "index";
    }


//    @GetMapping("/predicate/{population}")
//    public String predicatex(Model model, @PathVariable String population) {
//
//        List<City> list = cityDAO.findAll(
//                new CityPredicate(
//                        new CustomCondition(population, "population", "gt"),
//                        new CustomCondition("Shandong", "district", "eq"))
//        );
//        System.out.println(list);
//        model.addAttribute("cities", list);
//        return "index";
//    }


    @GetMapping("/predicate/{population}")
    public String predicate(Model model, @PathVariable String population) {

        List<City> list = cityDAO.findAll(
                CitySpecification.equalSpecification(new CustomCondition(population, "population", "eq"))
        );

        System.out.println(list);
        model.addAttribute("cities", list);
        return "index";
    }


}
