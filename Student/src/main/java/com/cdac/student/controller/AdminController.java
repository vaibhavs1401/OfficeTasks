package com.cdac.student.controller;

import com.cdac.student.entity.Student;
import com.cdac.student.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')") // everything here is ADMIN-only
public class AdminController {

    private final StudentService studentService;
    public AdminController(StudentService studentService) {
        this.studentService = studentService;
    }

    /** Get student by roll number */
    @GetMapping("/student")
    public String getStudentDetails(@RequestParam("rollNo") String rollNo, Model model) {
        Student s = studentService.getStudentByRollNo(rollNo);
        model.addAttribute("student", s);
        return "studentDetails";
    }

    /** Full list */
    @GetMapping("/studentlist")
    public String getStudentList(Model model) {
        List<Student> ls = studentService.getAllStudents();
        model.addAttribute("list", ls);
        return "studentList";
    }

    /** Class-wise list — use 'std' instead of reserved word 'class' */
    @GetMapping("/studentlist/std/{std}")
    public String getStudentListWithClass(@PathVariable("std") int std, Model model) {
        List<Student> ls = studentService.getStudentClassWise(std);
        model.addAttribute("list", ls);
        model.addAttribute("classStd", std);
        return "studentListClassWise";
    }

    /** Alternate class-wise endpoint (query param) */
    @GetMapping("/classwise")
    public String getStudentsByClass(@RequestParam("std") int std, Model model) {
        List<Student> students = studentService.findStudentsByStd(std);
        model.addAttribute("students", students);
        model.addAttribute("classStd", std);
        return "studentsByClass";
    }

    /** Show add form */
    @GetMapping("/student/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    /** Create student (links to existing UserAccount in service) */
    @PostMapping("/student/add")
    public String addStudent(@ModelAttribute Student student) {
        studentService.addStudent(student);
        return "redirect:/admin/studentlist";
    }

    /** Show update form (admin editing any student) */
    @GetMapping("/student/update")
    public String showUpdateForm(@RequestParam("rollNo") String rollNo, Model model) {
        Student s = studentService.findByRollNo(rollNo);
        model.addAttribute("student", s);
        return "updateStudent";
    }

    /** Persist update (admin) */
    @PostMapping("/student/update")
    public String updateStudent(@ModelAttribute Student student) {
        studentService.updateStudent(student);
        return "redirect:/admin/studentlist";
    }

    /** Delete by roll number */
    @PostMapping("/student/delete")
    public String deleteStudent(@RequestParam("rollNo") String rollNo) {
        studentService.deleteStudentByRollNo(rollNo);
        return "redirect:/admin/studentlist";
    }
}
