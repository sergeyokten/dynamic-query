package com.example.dynamicquery.controllers;

import com.example.dynamicquery.dao.CityDAO;
import com.example.dynamicquery.dao.CitySpecification;
import com.example.dynamicquery.models.City;
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
        Page<City> cityPage = cityDAO.findAll(CitySpecification.kabulName(),pageable);
        List<City> content = cityPage.getContent();
        System.out.println(content);
        model.addAttribute("cities", content);
        return "index";
    }


}
