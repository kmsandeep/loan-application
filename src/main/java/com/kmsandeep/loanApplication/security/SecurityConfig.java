package com.kmsandeep.loanApplication.security;

import com.kmsandeep.loanApplication.security.userservice.LoanUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    DataSource dataSource;
    public static final String DEF_USERS_BY_USERNAME_QUERY = """
            select username,password,enabled from loan_users where username = ?
            """;

    public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = """
            select u.username,a.authority from loan_users u join loan_user_authorities ua on u.id = ua.user_id join loan_authorities a on 
            a.id=ua.authority_id where u.username = ?
            """;


    @Bean
    UserDetailsService userDetailsService() {
        return new LoanUserDetailService();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/addUser").permitAll()
                        .requestMatchers("/user/addRole").permitAll()
                        .requestMatchers("/loan/findAll").hasRole("VIEW")
                        .requestMatchers("/loan/*").hasRole("USER")
                        .requestMatchers("/user/listUser").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}

