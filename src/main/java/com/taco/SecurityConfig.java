package com.taco;

import com.taco.domain.User;
import com.taco.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        return  httpSecurity.authorizeRequests()
                .antMatchers("/h2-console").permitAll()
                .antMatchers("/design","/orders").hasRole("USER")
                .antMatchers("/","/**").permitAll()
                 .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/design")
                 .and()
                    .csrf()
                    .disable()
                .   build();

    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            User user=userRepository.findByUsername(username);
            if(user != null)
                return user;
            throw new UsernameNotFoundException("User "+username+" not found");
        };
    }
}
