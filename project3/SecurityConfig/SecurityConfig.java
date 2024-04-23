package com.example.project3.SecurityConfig;


import com.example.project3.Sevice.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;




    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());


        return authProvider;
    }



    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/customer/register" , "/api/v1/employee/register" ).permitAll()
                .requestMatchers("/api/v1/auth/delete/{userId}" , "/api/v1/auth/get_all_user").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/auth/get").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/auth/get").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/auth/update_user").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/auth/update_user").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/customer/add").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/new_account" , "/api/v1/account/details").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/deposit/{accountId}/{amount}" , "/api/v1/account/withdraw/{accountId}/{amount}"
                        , "/api/v1/account/transfer/{userAccountId}/{anotherUserId}/{amount}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/employee/active_account" , "/api/v1/employee/block/{accountId}").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/employee/list_users").hasAuthority("ADMIN")
                .and()
                .logout().logoutUrl("/api/v1/auth/logout").permitAll()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();

        return http.build();
    }



}
