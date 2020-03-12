package com.sanwish.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Sanwish on 2020/3/7.
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
