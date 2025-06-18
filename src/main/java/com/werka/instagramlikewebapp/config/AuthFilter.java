package com.werka.instagramlikewebapp.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI().substring(request.getContextPath().length());

        // Ścieżki bez autoryzacji
        if (path.startsWith("/styles/") ||
                path.startsWith("/scripts/") ||
                path.startsWith("/images/") ||
                path.equals("/") ||
                path.equals("/login") ||
                path.equals("/registration")) {

            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = request.getSession(false);
        Object user = (session != null) ? session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect( request.getContextPath() + "/");
            //request.getRequestDispatcher("/WEB-INF/start/index.jsp").forward(request, response);
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }
}
