package com.spring.blog.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_LIST = {
            "/",
            "/posts",
            "/post/{id}"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers(AUTH_LIST).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/entrar").permitAll().failureUrl("/entrar?error").permitAll().defaultSuccessUrl("/posts", true)
                .and().logout().logoutSuccessUrl("/entrar?logout").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("alexandre.nascimento").password("{noop}123").roles("ADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers( "/css/**", "/js/**","/fonts/**","/images/**");
    }


}
