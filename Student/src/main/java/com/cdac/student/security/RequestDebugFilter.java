package com.cdac.student.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestDebugFilter implements Filter {
  @Override
  public void doFilter(ServletRequest r, ServletResponse s, FilterChain c)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) r;
    System.out.println("[REQ] method=" + req.getMethod()
        + " uri=" + req.getRequestURI()
        + " ctx=" + req.getContextPath()
        + " sp=" + req.getServletPath()
        + " qp=" + req.getQueryString());
    c.doFilter(r, s);
  }
}
