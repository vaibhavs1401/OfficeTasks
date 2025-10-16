/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.simpleretrievalsystem.service;

import com.cdac.simpleretrievalsystem.dto.cookie.CookieConsentDto;
import com.cdac.simpleretrievalsystem.model.CookieConsent;
import com.cdac.simpleretrievalsystem.repository.CookieConsentRepository;
import com.cdac.simpleretrievalsystem.utils.HttpUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author hcdc
 */

@Service
@RequiredArgsConstructor
@Transactional
public class CookieConsentServiceImplementation implements CookieConsentService{
    
    private final CookieConsentRepository repository;
    private final ObjectMapper objectMapper;

    @Override
    public CookieConsent saveFromDto(CookieConsentDto dto, HttpServletRequest request) {
        CookieConsent entity = CookieConsent.builder()
                .userId(dto.getUserId())
                .sessionId(dto.getSessionId())
                .consentGiven(Boolean.TRUE.equals(dto.getConsentGiven()))
                .consentCategories(dto.getConsentCategories())
                .consentVersion(dto.getConsentVersion())
                .consentSource(dto.getConsentSource())
                .decisionDate(LocalDateTime.now())
                .consentWithdrawn(Boolean.FALSE.equals(dto.getConsentGiven()) ? false : false) // explicit
                .ipAddress(HttpUtils.getClientIp(request))
                .userAgent(request != null ? request.getHeader("User-Agent") : null)
                .language(dto.getLanguage())
                .policyUrl(dto.getPolicyUrl())
                .build();
        if (dto.getCookieDetails() != null) {
            try {
                entity.setCookieDetails(objectMapper.writeValueAsString(dto.getCookieDetails()));
            } catch (JsonProcessingException e) {
                entity.setCookieDetails("{}");
            }
        }
        
        return repository.save(entity);
    }

    @Override
    public Optional<CookieConsent> getBySessionId(String sessionId) {
         if (sessionId == null) return Optional.empty();
               return repository.findFirstBySessionIdOrderByDecisionDateDesc(sessionId);
    }

    @Override
    public CookieConsent withdrawConsent(Long id) {
        CookieConsent c = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consent not found"));
        c.setConsentWithdrawn(true);
        c.setWithdrawnAt(LocalDateTime.now());
        c.setConsentGiven(false);
        return repository.save(c);
    }
    
}
