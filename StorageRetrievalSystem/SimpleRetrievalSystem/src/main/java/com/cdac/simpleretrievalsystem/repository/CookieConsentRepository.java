/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cdac.simpleretrievalsystem.repository;

import com.cdac.simpleretrievalsystem.model.CookieConsent;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hcdc
 */
public interface CookieConsentRepository extends JpaRepository<CookieConsent, Long> {
   Optional<CookieConsent> findFirstBySessionIdOrderByDecisionDateDesc(String sessionId);
   Optional<CookieConsent> findByUserIdOrderByDecisionDateDesc(String userId);
}
