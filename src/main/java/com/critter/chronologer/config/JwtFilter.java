package com.critter.chronologer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * this is a jwt filter that filters every incoming requests  and
 * it extends the once per request filter and implements one method
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    private String userName;
    private String token;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // extract the Authorization header from the request
        String authorizationHeader = request.getHeader("Authorization");
        // check if the authorization header contains the token
        // and also starts with the "Bearer Token"
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ") ){
            // the token will be the string at index after the Bearer
            token= authorizationHeader.substring(7);
            // get the userName from the token
            userName = jwtUtils.getUsernameFromToken(token);

        }
        // check if the userName is not null and also check if the user session does not exist with user details
        if(userName != null && SecurityContextHolder.getContext().getAuthentication()== null){
            // load the user
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            // validate token
            if(jwtUtils.validateToken(token,userDetails)){
                // create a new authentication token
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );
                //set the user authentication token using the request
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                //set the security context
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        // do filter
        filterChain.doFilter(request,response);
        // then got to the webSecurityConfiguration and implement the filter
    }
}