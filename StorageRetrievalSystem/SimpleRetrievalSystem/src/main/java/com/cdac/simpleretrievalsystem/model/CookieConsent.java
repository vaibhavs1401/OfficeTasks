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
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
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
    private LocalDateTime decisionDate;
    private String ipAddress;
    private String userAgent;

}
