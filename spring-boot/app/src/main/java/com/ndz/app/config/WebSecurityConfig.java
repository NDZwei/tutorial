package com.ndz.app.config;

import com.ndz.app.security.AuthEntryPointJwt;
import com.ndz.app.security.AuthenticationFilter;
import com.ndz.app.security.AuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


/*
    author: NMDuc
    created_at: 2/24/2024
    github: https://github.com/NDZwei
*/
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private Environment env;

    @Resource
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManagerBean());
        authenticationFilter.setFilterProcessesUrl("/oauth/token");
        http.csrf().disable()
                .anonymous().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(STATELESS).and()
                .authorizeHttpRequests()
                .antMatchers("/oauth/token/**").permitAll()
                .antMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(corsFilter(), CorsFilter.class);
        http.addFilter(authenticationFilter);
        http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        String allowedCredentials = env.getProperty("endpoints.cors.allow-credentials");
        String allowedHeaders = env.getProperty("endpoints.cors.allowed-headers");
        String allowedMethods = env.getProperty("endpoints.cors.allowed-methods");
        String allowedOrigins = env.getProperty("endpoints.cors.allowed-origins");
        config.setAllowCredentials(Boolean.parseBoolean(allowedCredentials));
        assert allowedHeaders != null;
        config.setAllowedHeaders(Arrays.asList(allowedHeaders.split(",")));
        assert allowedMethods != null;
        config.setAllowedMethods(Arrays.asList(allowedMethods.split(",")));
        assert allowedOrigins != null;
        config.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
