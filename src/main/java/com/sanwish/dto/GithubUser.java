package com.sanwish.dto;

import lombok.Data;

/**
 * Created by Sanwish on 2020/2/19.
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

}
