package com.banyechan.comprehensive.banyechan_tomcat_war.controller;

import com.banyechan.comprehensive.banyechan_tomcat_war.entity.StudentModel;
import com.banyechan.comprehensive.banyechan_tomcat_war.mapper.StudentModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentModelMapper studentMapper;

    @RequestMapping("/add")
    public boolean addStuent(StudentModel studentModel) {
        int result = studentMapper.insertSelective(studentModel);
        return result > 0;
    }

    @RequestMapping("/get")
    public StudentModel addStuent(Integer studentId) {
        StudentModel result = studentMapper.selectByPrimaryKey(studentId);
        return result;
    }


}
