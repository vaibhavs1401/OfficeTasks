// src/main/java/com/cdac/student/controller/StudentController.java
package com.cdac.student.controller;

import com.cdac.student.entity.BaseEntity.UserRole;
import com.cdac.student.entity.Student;
import com.cdac.student.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
}
