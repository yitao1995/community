package com.sanwish.dto;

import com.sanwish.model.User;
import lombok.Data;

/**
 * Created by Sanwish on 2020/2/23.
 */
@Data
public class QuestionDTO {

    private Long id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private Long creator;
    private String tag;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private User user;

}



