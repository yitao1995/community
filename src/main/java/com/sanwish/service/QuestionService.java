package com.sanwish.service;

import com.sanwish.dto.PaginationDTO;
import com.sanwish.dto.QuestionDTO;
import com.sanwish.mapper.QuestionMapper;
import com.sanwish.mapper.UserMapper;
import com.sanwish.model.Question;
import com.sanwish.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sanwish on 2020/2/23.
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO List(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = questionMapper.count();

        Integer totalPage;

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.List(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();


        for (Question question : questions) {
            User user = userMapper.findByid(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);


        return paginationDTO;
    }

    public PaginationDTO List(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        //总页数
        Integer totalPage;
        //个人问题总数
        Integer totalCount = questionMapper.countByUserId(userId);

        //计算总页数，
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //对页数数据合理性检测
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.ListByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();

        for (Question question : questions) {
            User user = userMapper.findByid(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;

    }

    public QuestionDTO getById(Integer id) {

        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.getById(id);
        User user = userMapper.findByid(question.getCreator());
        questionDTO.setUser(user);
        BeanUtils.copyProperties(question, questionDTO);

        return questionDTO;
    }

    public void createOrUpdate(Question question) {

        if(question.getId()==null){
            //创建问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);

        }else{
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }



    }
}
