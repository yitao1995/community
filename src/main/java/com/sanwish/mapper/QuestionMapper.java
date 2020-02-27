package com.sanwish.mapper;

import com.sanwish.dto.QuestionDTO;
import com.sanwish.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import java.util.List;

/**
 * Created by Sanwish on 2020/2/21.
 */
@Component
@Mapper
public interface QuestionMapper {


    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> List(@Param("offset") Integer offset,
                        @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    List<Question> ListByUserId(@Param("userId") Integer userId,
                                @Param("offset") Integer offset,
                                @Param("size") Integer size);

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(@Param("userId") Integer userId);

    @Select("select * from question where id = #{id}")
    Question getById(@Param("id") Integer id);

    @Update("UPDATE question SET title = #{title}, tag = #{tag} , description = #{description} , gmt_Modified = #{gmtModified} where id = #{id} ")
    void update(Question dbQuestion);

}
