package com.sanwish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Sanwish on 2020/2/19.
 */
@Controller
public class HelloController {


    @GetMapping("/hello")
    public String hello(@RequestParam(name="name") String name ,Model model){

        model.addAttribute("name",name);
        return "hello";

    }
}
