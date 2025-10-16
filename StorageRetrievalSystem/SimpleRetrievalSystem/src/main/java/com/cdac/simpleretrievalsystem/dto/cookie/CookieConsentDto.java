/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.simpleretrievalsystem.dto.cookie;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;

/**
 *
 * @author hcdc
 */


@Data
public class CookieConsentDto {
    private Long id;
    private String userId;
    private String sessionId;
    private Boolean consentGiven;
    private String consentCategories; 
    private Map<String, Boolean> cookieDetails; 
    private String consentVersion;
    private String consentSource;
    private LocalDateTime decisionDate;
    private Boolean consentWithdrawn;
    private LocalDateTime withdrawnAt;
    private String ipAddress;
    private String userAgent;
    private String language;
    private String policyUrl; 
}
