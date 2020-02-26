package com.sanwish.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.sanwish.mapper.QuestionMapper;
import com.sanwish.mapper.UserMapper;
import com.sanwish.model.Question;
import com.sanwish.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sanwish on 2020/2/19.
 */
@Controller
public class PublishController {

    //自动注入
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;


    @GetMapping("/publish")
    public String publish() {
        return "publish.html";
    }

    @PostMapping("/publish")
    public String create(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || title == "") {
            model.addAttribute("error", "问题不能为空");
            return "/publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "description不能为空");
            return "/publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "tag不能为空");
            return "/publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                }
            }
        if (user == null) {
            //用户不存在
            model.addAttribute("error", "用户不存在,请登录");
            return "/publish";
        }
        Question question = new Question();

        question.setCreator(user.getId());
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionMapper.create(question);

        //返回首页
        return "redirect:/";
    }


}
