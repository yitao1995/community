package com.sanwish.controller;

import com.sanwish.GithubProvider.GithubProvider;
import com.sanwish.dto.AccessTokenDTO;
import com.sanwish.dto.GithubUser;
import com.sanwish.model.User;
import com.sanwish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by Sanwish on 2020/2/19.
 */
@Controller
public class AuthorizeController {

    //ctrl+e 切换最近窗口
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Autowired
    private UserService userService;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse reponse) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        //封装 accessTokenDTO
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);

        GithubUser githubUser = githubProvider.getUser(accessToken);

        if (githubUser != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setToken(token);
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.createOrUpdate(user);
            reponse.addCookie(new Cookie("token", token));
            return "redirect:/";

        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        //返回主页
        return "redirect:/";
    }

}
