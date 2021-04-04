package com.spring.blog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_LIST = {
            "/",
            "/posts",
            "/post/{id}"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers(AUTH_LIST).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/entrar").permitAll().failureUrl("/entrar?error").permitAll().defaultSuccessUrl("/posts", true)
                .and().logout().logoutSuccessUrl("/entrar?logout").permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers( "/css/**", "/js/**","/fonts/**","/images/**");
    }


}
