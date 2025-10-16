/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.simpleretrievalsystem.service;

import com.cdac.simpleretrievalsystem.dto.cookie.CookieConsentDto;
import com.cdac.simpleretrievalsystem.model.CookieConsent;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 *
 * @author hcdc
 */
public interface CookieConsentService {

    public CookieConsent saveFromDto(CookieConsentDto dto, HttpServletRequest request);

    public Optional<CookieConsent> getBySessionId(String sessionId);

    public CookieConsent withdrawConsent(Long id);
    
}
