package com.cdac.student.controller;

import com.cdac.student.dao.RoleDao;
import com.cdac.student.entity.Role;
import com.cdac.student.entity.Student;
import com.cdac.student.entity.UserAccount;
import com.cdac.student.service.StudentService;
import com.cdac.student.service.UserAccountService;
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
    private final UserAccountService userAccountService;
    private final RoleDao roleDao;

    public AdminController(StudentService studentService, UserAccountService userAccountService, RoleDao roleDao) {
        this.studentService = studentService;
        this.userAccountService = userAccountService;
        this.roleDao = roleDao;
    }

    /**
     * Get student by roll number
     */
    @GetMapping("/student")
    public String getStudentDetails(@RequestParam("rollNo") String rollNo, Model model) {
        Student s = studentService.getStudentByRollNo(rollNo);
        model.addAttribute("student", s);
        return "studentDetails";
    }

    /**
     * Full list
     */
    @GetMapping("/studentlist")
    public String getStudentList(Model model) {
        List<Student> ls = studentService.getAllStudents();
        model.addAttribute("list", ls);
        return "studentList";
    }

    /**
     * Class-wise list — use 'std' instead of reserved word 'class'
     */
    @GetMapping("/studentlist/std/{std}")
    public String getStudentListWithClass(@PathVariable("std") int std, Model model) {
        List<Student> ls = studentService.getStudentClassWise(std);
        model.addAttribute("list", ls);
        model.addAttribute("classStd", std);
        return "studentListClassWise";
    }

    /**
     * Alternate class-wise endpoint (query param)
     */
    @GetMapping("/classwise")
    public String getStudentsByClass(@RequestParam("std") int std, Model model) {
        List<Student> students = studentService.findStudentsByStd(std);
        model.addAttribute("students", students);
        model.addAttribute("classStd", std);
        return "studentsByClass";
    }

    /**
     * Show add form
     */
    @GetMapping("/student/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    /**
     * Create student (links to existing UserAccount in service)
     *
     * @param student
     */
    @PostMapping("/student/add")
    public String addStudent(@ModelAttribute Student student) {
        studentService.addStudent(student);
        return "redirect:/admin/studentlist";
    }

    /**
     * Show update form (admin editing any student)
     */
    @GetMapping("/student/update")
    public String showUpdateForm(@RequestParam("rollNo") String rollNo, Model model) {
        Student s = studentService.findByRollNo(rollNo);
        model.addAttribute("student", s);
        return "updateStudent";
    }

    /**
     * Persist update (admin)
     */
    @PostMapping("/student/update")
    public String updateStudent(@ModelAttribute Student student) {
        studentService.updateStudent(student);
        return "redirect:/admin/studentlist";
    }

    /**
     * Delete by roll number
     */
    @PostMapping("/student/delete")
    public String deleteStudent(@RequestParam("rollNo") String rollNo) {
        studentService.deleteStudentByRollNo(rollNo);
        return "redirect:/admin/studentlist";
    }

    @GetMapping("/student/addWithAccount")
    public String showAddStudentWithAccountForm(Model model) {
        model.addAttribute("student", new Student());
        return "addStudentWithAccount"; // JSP above
    }

    @PostMapping("/student/addWithAccount")
    public String addStudentWithAccount(@ModelAttribute Student student, Model model) {
        try {
            // 1. Extract user account info from nested student.account
            UserAccount account = student.getAccount();

            if (account == null) {
                model.addAttribute("error", "UserAccount info is required.");
                return "addStudentWithAccount";
            }

            // 2. Check if email already exists
            if (userAccountService.existsByEmail(account.getEmail())) {
                model.addAttribute("error", "Email is already registered.");
                return "addStudentWithAccount";
            }

            // 3. Encode password (IMPORTANT)
            String encodedPassword = userAccountService.encodePassword(account.getPassword());
            account.setPassword(encodedPassword);

            Role studentRole = roleDao.findByName(Role.RoleName.ROLE_STUDENT);
            if (studentRole == null) {
                studentRole = new Role(Role.RoleName.ROLE_STUDENT);
                studentRole = roleDao.save(studentRole);
            }
            account.getRoles().add(studentRole);

            // 4. Save user account
            UserAccount savedAccount = userAccountService.save(account);

            // 5. Link student with saved account
            student.setAccount(savedAccount);

            // 6. Save student
            studentService.addStudent(student);

            return "redirect:/admin/studentlist";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "addStudentWithAccount";
        }
    }
    
    
    @GetMapping("/search")
    public String showSearchPage(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null && !name.isBlank()) {
            List<Student> students = studentService.findByName(name);
            model.addAttribute("students", students);
        }
        model.addAttribute("name", name == null ? "" : name);
        return "searchStudent";
    }
    
}
