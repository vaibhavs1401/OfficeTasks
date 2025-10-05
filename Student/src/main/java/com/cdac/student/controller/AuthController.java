package com.cdac.student.controller;

import com.cdac.student.entity.UserAccount;
import com.cdac.student.security.jwt.JwtUtils;
import com.cdac.student.service.RegistrationService;
import com.cdac.student.service.StudentService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final StudentService studentService;
    private final AuthenticationManager authManager;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;                  // ✅ inject it
    private final RegistrationService registrationService; // ✅ inject it

    public AuthController(StudentService studentService,
                          AuthenticationManager authManager,
                          PasswordEncoder encoder,
                          JwtUtils jwtUtils,
                          RegistrationService registrationService) {
        this.studentService = studentService;
        this.authManager = authManager;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.registrationService = registrationService;
    }

    // ---------- Views (JSP) ----------

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    /**
     * Form login (JSP).On success: issues JWT cookie, then redirects
 based on role.
     * @param email
     * @param password
     * @param response
     * @param model
     * @return 
     */
@PostMapping("/login")
public String doFormLogin(@RequestParam("email") String email,
                          @RequestParam("password") String password,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          Model model) {
    try {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // Make the auth visible to the rest of the request pipeline
        SecurityContextHolder.getContext().setAuthentication(auth);

        // principal is your UserAccount (implements UserDetails)
        UserAccount principal = (UserAccount) auth.getPrincipal();

        // (Only if other code expects it) put the user in session
        request.getSession(true).setAttribute("currentUser", principal);

        // Issue JWT (once)
        String jwt = jwtUtils.generateToken(principal); // or use username/id/roles if your util expects that
        addJwtCookie(response, jwt, Duration.ofHours(1));

        // Check role from authorities (don't cast Authentication to UserAccount)
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()));

        return isAdmin ? "redirect:/admin/studentlist" : "redirect:/student/profile";

    } catch (BadCredentialsException ex) {
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }
}


    @GetMapping("/register")
    public String registerPage() { return "register"; }

    /**
     * Form registration (JSP).Delegates to RegistrationService to:
  - create UserAccount (BCrypt)
  - assign ROLE_STUDENT
  - create linked Student
     * @param email
     * @param password
     * @return 
     */
    @PostMapping("/register")
    public String registerStudent(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              @RequestParam("name") String name,
                              @RequestParam(value = "standard", required = false) Integer standard,
                              @RequestParam(value = "std", required = false) Integer std,   // ← accept both
                              @RequestParam(value = "age", required = false) Integer age,
                              @RequestParam(value = "rollNo", required = false) String rollNo,
                              Model model) {
    try {
        Integer finalStd = (standard != null) ? standard : std;
        var req = new RegistrationService.RegisterRequest(
            email, password, name, finalStd, age, rollNo
        );
        registrationService.registerStudent(req);
        return "redirect:/auth/login?registered";
    } catch (IllegalArgumentException e) {
        model.addAttribute("error", e.getMessage());
        return "register";
    } catch (org.springframework.dao.DataIntegrityViolationException ex) {
        // e.g., unique constraint on email or roll_no
        model.addAttribute("error", "Duplicate email or roll number.");
        return "register";
    } catch (Exception e) {
        model.addAttribute("error", "Registration failed");
        return "register";
    }
}


    /**
     * Clear the JWT cookie (stateless logout).
     * @param response
     * @return 
     */
    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        clearJwtCookie(response);
        return "redirect:/auth/login?logout";
    }

    // ---------- API (JSON) ----------

    public record LoginRequest(@NotBlank String email, @NotBlank String password) {}
    public record LoginResponse(String token, String role, String redirect) {}

    @PostMapping(value = "/api/login", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public LoginResponse apiLogin(@RequestBody LoginRequest req, HttpServletResponse response) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password())
        );
        UserAccount principal = (UserAccount) auth.getPrincipal();

        String jwt = jwtUtils.generateToken(principal);
        addJwtCookie(response, jwt, Duration.ofHours(1));

        String role = firstRole(principal);
        String redirect = hasRole(principal, "ROLE_ADMIN") ? "/admin/studentlist" : "/student/profile";
        return new LoginResponse(jwt, role, redirect);
    }

    // ---------- Helpers ----------

    private void addJwtCookie(HttpServletResponse response, String token, Duration ttl) {
        Cookie cookie = new Cookie("JWT", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // set true if you serve over HTTPS
        cookie.setPath("/");
        cookie.setMaxAge((int) ttl.getSeconds());
        // SameSite attr via header (for broad container support)
        String attrs = "JWT=" + token + "; Max-Age=" + cookie.getMaxAge() + "; Path=/; HttpOnly; SameSite=Lax";
        if (cookie.getSecure()) attrs += "; Secure";
        response.addHeader("Set-Cookie", attrs);
        response.addCookie(cookie);
    }

    private void clearJwtCookie(HttpServletResponse response) {
        response.addHeader("Set-Cookie", "JWT=; Max-Age=0; Path=/; HttpOnly; SameSite=Lax");
    }

    private boolean hasRole(UserAccount principal, String roleName) {
        for (GrantedAuthority ga : principal.getAuthorities()) {
            if (roleName.equals(ga.getAuthority())) return true;
        }
        return false;
    }

    private String firstRole(UserAccount principal) {
        return principal.getAuthorities().stream()
                .findFirst().map(GrantedAuthority::getAuthority).orElse("ROLE_STUDENT");
    }
    
    
}
