package com.sanwish.dto;

import lombok.Data;

/**
 * Created by Sanwish on 2020/2/19.
 */
@Data
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
