package com.aitnacer.LabXpert.security;

import com.aitnacer.LabXpert.filter.JWTAuthenticationFilter;
import com.aitnacer.LabXpert.filter.JWTAuthorizationFilter;
import com.aitnacer.LabXpert.helper.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTHelper jwtHelper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/user/**", "/api/v1/fournisseur/**", "/api/v1/reactif/**")
                .hasAnyAuthority("RESPONSABLE", "TECHNICIEN")
                .antMatchers(HttpMethod.POST, "/api/v1/user/**", "/api/v1/fournisseur/**", "/api/v1/reactif/**")
                .hasAuthority("RESPONSABLE")
                .antMatchers(HttpMethod.PUT, "/api/v1/user/**", "/api/v1/fournisseur/**", "/api/v1/reactif/**")
                .hasAuthority("RESPONSABLE")
                .antMatchers(HttpMethod.DELETE, "/api/v1/user/**", "/api/v1/fournisseur/**", "/api/v1/reactif/**")
                .hasAuthority("RESPONSABLE")
                .antMatchers("/api/v1/analyse/**").hasAnyAuthority("RESPONSABLE", "TECHNICIEN")
                .anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)),jwtHelper));
        http.addFilterBefore(new JWTAuthorizationFilter(jwtHelper), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

}