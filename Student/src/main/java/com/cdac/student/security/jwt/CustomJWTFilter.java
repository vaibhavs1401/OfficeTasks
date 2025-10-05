package com.cdac.student.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomJWTFilter extends OncePerRequestFilter {

    private static final String JWT_COOKIE_NAME = "JWT";

    private final JwtUtils jwtUtils;
    private final UserDetailsService uds;

    public CustomJWTFilter(JwtUtils jwtUtils, UserDetailsService uds) {
        this.jwtUtils = jwtUtils;
        this.uds = uds;
    }

    // (Optional) don’t even try to parse JWT on public paths
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String p = request.getServletPath();
        return p.equals("/") ||
               p.equals("/index") || p.equals("/index.html") || p.equals("/index.jsp") ||
               p.startsWith("/auth/") ||
               p.startsWith("/css/") || p.startsWith("/js/") || p.startsWith("/images/") ||
               p.startsWith("/webjars/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        try {
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                String token = resolveToken(request);

                if (StringUtils.hasText(token) && jwtUtils.validate(token)) {
                    String username = jwtUtils.extractUsername(token);

                    // ⚠️ This can throw UsernameNotFoundException (AuthenticationException)
                    UserDetails userDetails = uds.loadUserByUsername(username);

                    var auth = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    context.setAuthentication(auth);
                    SecurityContextHolder.setContext(context);
                }
            }
            chain.doFilter(request, response);
        } catch (org.springframework.security.core.AuthenticationException ex) {
            // Token is valid but user is gone/disabled → treat as anonymous, optionally clear cookie
            clearJwtCookie(response);
            // Continue as unauthenticated instead of triggering the entry point
            chain.doFilter(request, response);
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (JWT_COOKIE_NAME.equals(c.getName()) && StringUtils.hasText(c.getValue())) {
                    return c.getValue();
                }
            }
        }
        return null;
    }

    private void clearJwtCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(JWT_COOKIE_NAME, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        // add SameSite attr if you were using it when setting the cookie
        response.addCookie(cookie);
        response.addHeader("Set-Cookie", "JWT=; Max-Age=0; Path=/; HttpOnly; SameSite=Lax");
    }
}

