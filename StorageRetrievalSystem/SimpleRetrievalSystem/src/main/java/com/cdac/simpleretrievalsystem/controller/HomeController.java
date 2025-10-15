/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.simpleretrievalsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author hcdc
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "homepage";
    }

   @GetMapping("/whoami")
   @ResponseBody
    public String whoami(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        return "Your IP: " + ip;
    }

}
