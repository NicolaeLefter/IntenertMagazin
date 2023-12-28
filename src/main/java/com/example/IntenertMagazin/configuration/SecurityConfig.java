package com.example.IntenertMagazin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //specificam logica de securitate
public class SecurityConfig {

  //Hello Wor
    @Bean
    public SecurityFilterChain security(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.authorizeRequests()
                .requestMatchers("/save", "/get").permitAll()
                .requestMatchers("/api/smart/update/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .cors().disable().csrf().disable()
                .httpBasic();


        return httpSecurity.build();

     //   httpSecurity.formLogin(Customizer.withDefaults());  //toate requesturile sa fie autentificate(autorizate)    // sa fie autentificarea prin form


    }
    @Bean
    public BCryptPasswordEncoder cryptPasswordEncoder(){

        return new BCryptPasswordEncoder();

    }
   /* @Bean
    public SecurityFilterChain security(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();   //toate requesturile sa fie autentificate(autorizate)
        httpSecurity.formLogin(); // sa fie autentificarea prin form
        return httpSecurity.build();

    } */
  /*  @Bean
    public NoOpPasswordEncoder passwordEncoder(){

        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();


    } */




}
