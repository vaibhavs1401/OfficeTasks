package com.cdac.student.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import com.cdac.student.security.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;

@Component
public class CustomJWTFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String JWT_COOKIE = "JWT";

    private final JwtUtils jwtUtils;
    private final UserDetailsService uds;

    public CustomJWTFilter(JwtUtils jwtUtils, UserDetailsService uds) {
        this.jwtUtils = jwtUtils;
        this.uds = uds;
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest req) {
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) return true;

        String uri = req.getRequestURI();   // e.g. /Student
        String ctx = req.getContextPath();  // e.g. /Student
        if (uri.equals(ctx) || uri.equals(ctx + "/")) return true; // exact root

        String sp = req.getServletPath();
        if (!StringUtils.hasText(sp)) sp = "/"; // normalize

        return sp.equals("/") || sp.equals("/index") || sp.equals("/home")
            || sp.equals("/index.html") || sp.equals("/index.jsp")
            || sp.startsWith("/auth/")
            || sp.startsWith("/css/") || sp.startsWith("/js/") || sp.startsWith("/images/")
            || sp.startsWith("/includes/") || sp.startsWith("/webjars/")
            || sp.equals("/favicon.ico");
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {
        try {
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                String token = resolveToken(request);
                if (StringUtils.hasText(token) && jwtUtils.validate(token)) {
                    String username = jwtUtils.extractUsername(token);
                    System.out.println(username);
                    UserDetails userDetails = uds.loadUserByUsername(username);
                    System.out.println(userDetails.toString());
                    Authentication auth = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    ((UsernamePasswordAuthenticationToken) auth)
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContext ctx = SecurityContextHolder.createEmptyContext();
                    ctx.setAuthentication(auth);
                    System.out.println(auth.toString());
                    SecurityContextHolder.setContext(ctx);
                }
            }
            chain.doFilter(request, response);
        } catch (AuthenticationException ex) {
            clearJwtCookie(response);
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String h = request.getHeader(AUTH_HEADER);
        if (StringUtils.hasText(h) && h.startsWith(BEARER)) return h.substring(BEARER.length());
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) if (JWT_COOKIE.equals(c.getName()) && StringUtils.hasText(c.getValue())) return c.getValue();
        }
        return null;
    }

    private void clearJwtCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(JWT_COOKIE, "");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.addHeader("Set-Cookie", "JWT=; Max-Age=0; Path=/; HttpOnly; SameSite=Lax");
    }
}


