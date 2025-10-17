package com.cdac.simpleretrievalsystem.interceptor;

import com.cdac.simpleretrievalsystem.model.CookieConsent;
import com.cdac.simpleretrievalsystem.service.CookieConsentService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CookieConsentInterceptor implements HandlerInterceptor {

    private final CookieConsentService cookieService;

    @Autowired
    public CookieConsentInterceptor(CookieConsentService cookieService) {
        this.cookieService = cookieService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String cookieConsent = null;
        String sessionId = null;

        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if ("cookieConsent".equals(c.getName())) cookieConsent = c.getValue();
                if ("sessionId".equals(c.getName())) sessionId = c.getValue();
            }
        }

        boolean accepted = "accepted".equalsIgnoreCase(cookieConsent);

        if (sessionId != null) {
            Optional<CookieConsent> opt = cookieService.getBySessionId(sessionId);
            if (opt.isPresent() && Boolean.TRUE.equals(opt.get().getConsentGiven()) && !Boolean.TRUE.equals(opt.get().getConsentWithdrawn())) {
                accepted = true;
                request.setAttribute("cookieConsentEntity", opt.get());
            }
        }

        request.setAttribute("cookieConsentAccepted", accepted);
        return true;
    }
}
