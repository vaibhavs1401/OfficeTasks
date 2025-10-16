/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.simpleretrievalsystem.interceptor;

import com.cdac.simpleretrievalsystem.service.CookieConsentService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author hcdc
 */
public class CookieConsentInterceptor implements HandlerInterceptor{
    
    
    private final CookieConsentService cookieService;
   
    public CookieConsentInterceptor(CookieConsentService cookieService){
        this.cookieService = cookieService;
    }
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response){
        
        String cookieConsent = null;
        String sessionId = null;
        
        if (request.getCookies() != null){
            for(Cookie c : request.getCookies()){
                if("cookieConsent".equals(c.getName())) cookieConsent = c.getName();
                if("sessionId".equals(c.getName())) sessionId = c.getValue();
                }
            }
        if("accepted".equalsIgnoreCase(cookieConsent)){
            request.setAttribute("cookiesConsentAccepted", true);
        }
        
    }
}
