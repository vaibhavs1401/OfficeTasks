/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.simpleretrievalsystem.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author hcdc
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String homepage(Model model, HttpServletRequest request) {
        String cookieConsent = Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(c -> "cookieConsent".equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
        model.addAttribute("showCookieBanner", true);
        return "homepage";
    }

    @GetMapping("/whoami")
    @ResponseBody
    public String whoami(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        return "Your IP: " + ip;
    }

}
