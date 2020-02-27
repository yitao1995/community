package com.sanwish.mapper;

import com.sanwish.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * Created by Sanwish on 2020/2/20.
 */
@Component
@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,gmt_Create,gmt_Modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findByid(@Param("id") Integer id);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("UPDATE user SET name = #{name}, token = #{token}, avatar_url = #{avatarUrl},gmt_Modified = #{gmtModified} where id = #{id} ")
    void update(User user);
}
