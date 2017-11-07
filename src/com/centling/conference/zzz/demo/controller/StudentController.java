package com.centling.conference.zzz.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.zzz.demo.entity.Student;
import com.centling.conference.zzz.demo.service.StudentService;

@Controller("studentController")
@RequestMapping("/student/*")
public class StudentController {

	@RequestMapping("/r")
	public @ResponseBody List<Student> findAll() {
		return this.studentService.findAll();
	}
	
	@Resource
	private StudentService studentService;
}
