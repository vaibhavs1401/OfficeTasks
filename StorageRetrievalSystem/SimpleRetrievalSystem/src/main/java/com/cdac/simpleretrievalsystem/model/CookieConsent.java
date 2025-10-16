/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cdac.simpleretrievalsystem.model;

/**
 *
 * @author hcdc
 */

import jakarta.persistence.*;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "cookie_consents",
    indexes = {
        @Index(name = "idx_user_id", columnList = "userId"),
        @Index(name = "idx_session_id", columnList = "sessionId"),
        @Index(name = "idx_decision_date", columnList = "decisionDate")
    }
)
public class CookieConsent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userId;
    
    
    private String sessionId;
    
    
    private Boolean consentGiven;
    

    @Column(columnDefinition = "TEXT")
    private String consentCategories;

    
    private String consentVersion;
    
    
    private String consentSource;
    
    
    @Column(columnDefinition = "JSON") 
    private String cookieDetails;
    
    private LocalDateTime decisionDate;
    
    @Builder.Default
    private Boolean consentWithdrawn = false;
    
    private LocalDateTime withdrawnAt;
    
  
    private String ipAddress;
    
    
    private String userAgent;
    
    
    private String country;
    
    
    private String language;
    
    
    private String policyUrl;
    
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    
    

}
