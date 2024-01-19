package com.GreenStitchassignmnet.GreenStitchassignmnet.filter;


import com.GreenStitchassignmnet.GreenStitchassignmnet.service.UsereinfoDetailService;
import com.GreenStitchassignmnet.GreenStitchassignmnet.auth.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UsereinfoDetailService usereinfoDetailService;

    @Autowired
    private  JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

            System.out.println(token);
            username = jwtTokenUtil.extractUsername(token);

            System.out.println(jwtTokenUtil.extractUsername(token));
            System.out.println(username);
        }
         try {
             if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                 UserDetails userDetails = usereinfoDetailService.loadUserByUsername(username);

                 if (jwtTokenUtil.validateToken(token, userDetails)) {

                     UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                     authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                     SecurityContextHolder.getContext().setAuthentication(authToken);


                 }

             }

         } catch (UsernameNotFoundException e) {
             throw new RuntimeException(e + "Invalid token");
         }

        filterChain.doFilter(request, response);
    }

}
