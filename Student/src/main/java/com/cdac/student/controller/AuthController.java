package com.cdac.student.controller;

import com.cdac.student.entity.Student;
import com.cdac.student.entity.BaseEntity.UserRole;
import com.cdac.student.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final StudentService studentService;

    public AuthController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @PostMapping("/login")
    public String doLogin(@RequestParam("email") String email,
                          @RequestParam("password") String password,
                          HttpServletRequest request,
                          Model model) {

        Student user = studentService.authenticate(email, password);
        if (user == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        var session = request.getSession(true);
        session.setAttribute("userId", user.getId());
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("userRole", user.getUserRole());
        session.setAttribute("userRollNo", user.getRollNo());


        if (user.getUserRole() == UserRole.ROLE_ADMIN) {
            return "redirect:/admin/studentlist";
        } else {
            return "redirect:/student/profile";
        }
    }

    @GetMapping("/register")
    public String register() { return "register"; }

    @PostMapping("/register")
    public String register(@ModelAttribute Student s, Model model) {
        String result = studentService.register(s);
        if ("EmailAlreadyExists".equals(result)) {
            model.addAttribute("error", "Email is already registered");
            return "register";
        }
        return "redirect:/auth/login?registered";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        var ses = req.getSession(false);
        if (ses != null) ses.invalidate();
        return "redirect:/login?logout";
    }
}
