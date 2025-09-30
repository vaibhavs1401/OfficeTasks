// src/main/java/com/cdac/student/controller/StudentController.java
package com.cdac.student.controller;

import com.cdac.student.entity.BaseEntity.UserRole;
import com.cdac.student.entity.Student;
import com.cdac.student.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest req, Model model) {
        var ses = req.getSession(false);
        if (ses == null) return "redirect:/login";
        Object role = ses.getAttribute("userRole");
        if (role != UserRole.ROLE_STUDENT) return "redirect:/login";

        String rollNo = (String) ses.getAttribute("userRollNo");
        if (rollNo == null) return "redirect:/login";

        Student s = studentService.getStudentByRollNo(rollNo);
        if (s == null) return "redirect:/login";

        model.addAttribute("student", s);
        return "studentDetails"; // reuse your existing JSP
    }
    
    
    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("rollNo") String rollNo, Model model){ 
        System.out.println(rollNo + "RollNo is here");
        Student student = studentService.findByRollNo(rollNo);
        model.addAttribute("student", student);
        return "updateStudent";
    }
    
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student){
        studentService.updateStudent(student);
        return "redirect:/admin/studentlist";
    }
    
    
    @PostMapping("/delete")
    public String deleteStudent(@RequestParam("rollNo") String rollNo){
        studentService.deleteStudentByRollNo(rollNo);
        return "redirect:/admin/studentlist";
    }
    
    
    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("student", new Student());
        return "addStudent";
    }
    
    
    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student){
        studentService.addStudent(student);
        return "redirect:/admin/studentlist";
    }
    
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        SecretKey key = "923rbuihfcfoiu";
        
    }
    
    
    
            
}
