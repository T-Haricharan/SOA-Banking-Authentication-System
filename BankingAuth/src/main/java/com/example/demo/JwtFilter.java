package com.example.demo;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain)
            throws ServletException, IOException {

        String header = req.getHeader("Authorization");

        if (req.getRequestURI().startsWith("/auth")) {
            chain.doFilter(req, res);
            return;
        }

        if (header != null && header.startsWith("Bearer ")
                && jwtService.validateToken(header.substring(7))) {

            chain.doFilter(req, res);

        } else {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}