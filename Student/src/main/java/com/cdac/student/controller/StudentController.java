package com.cdac.student.controller;

import com.cdac.student.entity.CustomUserDetails;
import com.cdac.student.entity.FileMetaData;
import com.cdac.student.entity.Student;
import com.cdac.student.entity.UserAccount;
import com.cdac.student.service.FileStorageService;
import com.cdac.student.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/student")
@PreAuthorize("hasRole('STUDENT')") // all endpoints require STUDENT by default
public class StudentController {

    private final StudentService studentService;
    private final FileStorageService fileStorageService;

    public StudentController(StudentService studentService, FileStorageService fileStorageService) {
        this.studentService = studentService;
        this.fileStorageService = fileStorageService;
    }

    /**
     * View my own profile (no session hacks)
     *
     * @param currentUser
     * @param request
     * @param model
     * @return
     */
    @Transactional
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
     *
     * @param currentUser
     * @param form
     * @return
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

    @GetMapping("/uploadfiles")
    public String fileHandle(@AuthenticationPrincipal CustomUserDetails currentUser, Model model) {
        UserAccount user = currentUser.getUserAccount();
        Student s = studentService.findByUserId(user.getId());
        model.addAttribute("student", s);
        return "uploadfiles";
    }

    @PostMapping("/uploadfiles")
    public String handleFileUpload(@AuthenticationPrincipal UserAccount currentUser,
            @RequestParam("files") MultipartFile[] files,
            RedirectAttributes redirectAttributes) {

        Student student = studentService.findByUserId(currentUser.getId());

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    fileStorageService.storeFile(file, student);
                } catch (IOException e) {
                    redirectAttributes.addFlashAttribute("error", "Failed to upload " + file.getOriginalFilename());
                    return "redirect:/uploadfiles";
                }
            }
        }

        redirectAttributes.addFlashAttribute("success", "Files uploaded successfully!");
        return "redirect:/uploadfiles";
    }

    @GetMapping("/myfiles")
    public String viewMyUploadedFiles(@AuthenticationPrincipal UserAccount currentUser, Model model) {
        // Fetch the Student entity linked to the logged-in user
        Student student = studentService.findByUserId(currentUser.getId());
        if (student == null) {
            model.addAttribute("error", "Student profile not found.");
            return "student/myfiles";  // JSP page even if empty/error message
        }

        // Retrieve the uploaded files for this student/user
        List<FileMetaData> uploadedFiles = fileStorageService.findFilesByUser(currentUser);

        // Add to model for display
        model.addAttribute("files", uploadedFiles);
        return "myfiles";  // JSP page to show files and statuses
    }

}
