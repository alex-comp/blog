package com.spring.blog.configuration;

import com.spring.blog.security.GpUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    GpUserDetailsService userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers(AUTH_LIST).permitAll().antMatchers("/newPost").hasRole("CRIAR_POST")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/entrar").permitAll().failureUrl("/entrar?error").permitAll().defaultSuccessUrl("/posts", true)
                .and().logout().logoutSuccessUrl("/entrar?logout").permitAll()
                .and().rememberMe().userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers( "/css/**", "/js/**","/fonts/**","/images/**");
    }


}
