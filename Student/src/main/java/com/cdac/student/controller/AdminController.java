package com.cdac.student.controller;

import com.cdac.student.entity.Student;
import com.cdac.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final StudentService studentService;

    public AdminController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public String getStudentDetails(@RequestParam("rollNo") String rollNo, Model model){
        Student s = studentService.getStudentByRollNo(rollNo);
        model.addAttribute("student", s);
        return "studentDetails";
    }

    // /admin/studentlist  (no 'class' parameter)
    @GetMapping(value = "/studentlist", params = "!class")
    public String getStudentList(Model model){
        List<Student> ls = studentService.getAllStudents();
        model.addAttribute("list", ls);
        return "studentList";
    }

    // /admin/studentlist?class=10
    @GetMapping(value = "/studentlist", params = "class")
    public String getStudentListWithClass(@RequestParam("class") int std, Model model){
        List<Student> ls = studentService.getStudentClassWise(std);
        model.addAttribute("list", ls);
        return "studentListClassWise";
    }
    
    
    @GetMapping("/classwise")
    public String getStudentsByClass(@RequestParam("std") int std, Model model){
        List<Student> students = studentService.findStudentsByStd(std);
        model.addAttribute("students", students);
        model.addAttribute("classStd", std);
        return "studentsByClass";
    }
    
}
