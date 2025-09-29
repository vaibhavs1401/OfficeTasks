/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.student.controller;

import com.cdac.student.entity.Student;
import com.cdac.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author hcdc
 */

@Controller
public class AuthController {
    
    private StudentService studentService;
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    
@PostMapping("/register")
public String register(@ModelAttribute Student s, Model model) {
    String result = studentService.register(s);
    if ("EmailAlreadyExists".equals(result)) {
        model.addAttribute("error", "Email is already registered");
        return "register";
    }
    return "redirect:/login?registered";

    }


}
