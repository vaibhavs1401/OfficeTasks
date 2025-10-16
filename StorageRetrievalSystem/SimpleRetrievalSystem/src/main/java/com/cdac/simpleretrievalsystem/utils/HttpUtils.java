/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.simpleretrievalsystem.utils;

import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author hcdc
 */
public class HttpUtils {
    
    public static String getClientIp(HttpServletRequest request){
        if(request == null) return null;

     String ip = request.getHeader("X-Forwarded-For");
     if(ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
         
         return ip.split(",")[0].trim();
     }
     
     ip = request.getHeader("X-Real-IP");
     if(ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)){
         return ip;
    }
     
    return request.getRemoteAddr();
    }
}
