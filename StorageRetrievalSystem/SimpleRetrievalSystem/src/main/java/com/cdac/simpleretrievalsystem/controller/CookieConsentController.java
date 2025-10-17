/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.simpleretrievalsystem.controller;

import com.cdac.simpleretrievalsystem.dto.cookie.CookieConsentDto;
import com.cdac.simpleretrievalsystem.model.CookieConsent;
import com.cdac.simpleretrievalsystem.service.CookieConsentService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcdc
 */
@RestController
@RequestMapping("/api/consent")
@AllArgsConstructor
public class CookieConsentController {
    
    @Autowired
    private final CookieConsentService cookieService;
    
    
    @PostMapping("/save")
    public ResponseEntity<CookieConsent> saveConsent(@RequestBody CookieConsentDto dto, HttpServletRequest request){
        CookieConsent saved = cookieService.saveFromDto(dto, request);
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<CookieConsent> getBySession(@PathVariable String sessionId) {
        Optional<CookieConsent> opt = cookieService.getBySessionId(sessionId);
        return opt.map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
    }
    
    @PostMapping("/withdraw/{id}")
    public ResponseEntity<CookieConsent> withdraw(@PathVariable Long id) {
        CookieConsent updated = cookieService.withdrawConsent(id);
        return ResponseEntity.ok(updated);
    }
    
}



