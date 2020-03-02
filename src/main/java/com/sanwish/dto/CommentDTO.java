package com.sanwish.dto;

import lombok.Data;

/**
 * Created by Sanwish on 2020/3/3.
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;


}
