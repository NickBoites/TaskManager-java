package com.metaphorce.taskmanager.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

//@Component
public class LoggingRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        StringBuilder payload = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            payload.append(line).append('\n');
        }

        // Aquí, puedes registrar el cuerpo de la solicitud
        System.out.println("Received payload: \n" + payload.toString());

        // Continuar con el resto de la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
