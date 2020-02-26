package com.sanwish.model;

import lombok.Data;

/**
 * Created by Sanwish on 2020/2/20.
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;


}
