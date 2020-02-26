package com.sanwish.controller;

import com.sanwish.dto.PaginationDTO;
import com.sanwish.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Sanwish on 2020/2/19.
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size) {

        PaginationDTO pagination = questionService.List(page, size);

        model.addAttribute("pagination", pagination);


        return "index.html";

    }
}
