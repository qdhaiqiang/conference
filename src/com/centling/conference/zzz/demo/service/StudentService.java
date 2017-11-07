package com.centling.conference.zzz.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.zzz.demo.DAO.StudentDAO;
import com.centling.conference.zzz.demo.entity.Student;


@Service("studentService")
public class StudentService {

	public List<Student> findAll() {
		return studentDAO.findAll();
	}
	
	@Resource
	private StudentDAO studentDAO;
}
