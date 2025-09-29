/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.student.controller;

import com.cdac.student.entity.Student;
import com.cdac.student.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RequestMapping(value = "/admin")
@Controller
public class AdminController {
    @Autowired
    private StudentService studentService;
    @GetMapping(value = "/student")
    public String getStudentDetails(@RequestParam("rollNo") String rollNo, Model model){
        Student s = studentService.getStudentByRollNo(rollNo);
        model.addAttribute("student", s);
        return "studentDetails";
    }
    
    @GetMapping(value = "/studentlist")
    public String getStudentList(Model model){
        List<Student> ls = studentService.getAllStudents();
        model.addAttribute("list", ls);
        return "studentList";
    }
    
    @GetMapping(value = "/studentlist")
    public String getStudentList(@RequestParam("class") String studentClass, Model model){
        List<Student> ls = studentService.getStudentClassWise(studentClass);
        model.addAttribute("list", ls);
        return "studentListClassWise";
    }
    
    
    
    
    
}




