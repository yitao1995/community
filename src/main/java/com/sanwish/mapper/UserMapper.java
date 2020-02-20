package com.sanwish.mapper;

import com.sanwish.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by Sanwish on 2020/2/20.
 */
@Component
@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,gmt_Create,gmt_Modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
     User findByToken(@Param("token") String token);
}
