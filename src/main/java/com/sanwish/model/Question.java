package com.sanwish.model;

import lombok.Data;

/**
 * Created by Sanwish on 2020/2/21.
 */
@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private String tag;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;


}

