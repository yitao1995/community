package com.sanwish.dto;

import com.sanwish.model.User;
import lombok.Data;

/**
 * Created by Sanwish on 2020/3/3.
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private String content;
    private User user;

    //javaBean的属性类型使用包装类型，不要使用基本类型。
}
