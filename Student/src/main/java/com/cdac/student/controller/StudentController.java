package com.cdac.student.controller;

import com.cdac.student.entity.Student;
import com.cdac.student.entity.UserAccount;
import com.cdac.student.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
@PreAuthorize("hasRole('STUDENT')") // all endpoints require STUDENT by default
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * View my own profile (no session hacks)
     *
     * @param currentUser
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserAccount currentUser,
            HttpServletRequest request,
            Model model) {
        if (currentUser == null) {
            System.out.println("Current user is null!");
            currentUser = (UserAccount) (request.getSession(false) != null
                    ? request.getSession(false).getAttribute("currentUser")
                    : null);
        }
        if (currentUser == null) {
            System.out.println("Current user is null!");
            return "redirect:/auth/login?error=Please+login";
        }

        var s = studentService.findByUserId(currentUser.getId());
        if (s == null) {
            System.out.println("Current user is null!");
            return "redirect:/auth/login?error=No+student+profile";
        }
        model.addAttribute("student", s);
        return "ownprofile";
    }

    
    /**
     * Show update form for my own profile
     *
     * @param currentUser
     * @param model
     * @return
     */
    @GetMapping("/update")
    public String showUpdateForm(@AuthenticationPrincipal UserAccount currentUser, Model model) {
        Student s = studentService.findByUserId(currentUser.getId());
        model.addAttribute("student", s);
        return "updateStudent";
    }

    /**
     * Persist my updates (server-side ensures current user owns the record)
     */
    @PostMapping("/update")
    public String updateStudent(@AuthenticationPrincipal UserAccount currentUser,
            @ModelAttribute @Valid Student form) {
        // Optional: enforce ownership in service layer as well
        studentService.updateOwnProfile(currentUser.getId(), form);
        return "redirect:/student/profile";
    }

    @GetMapping("/testprofile")
    public String testProfile() {
        return "ownprofile";
    }

}


