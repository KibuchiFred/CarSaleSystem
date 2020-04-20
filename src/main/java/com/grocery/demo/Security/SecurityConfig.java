package com.grocery.demo.Security;

import com.grocery.demo.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    public void configure (AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider());
    }

    //this method allows static resources to be neglected by spring security
    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**","/css/**", "/js/**","/fonts/**","/webjars/**");
    }


    @Override
    protected  void configure (HttpSecurity http)throws Exception{

        http.csrf().disable()

         .authorizeRequests()
                .antMatchers("/register").hasAuthority("ADMIN")
                .antMatchers("/activate-account").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/welcome").authenticated()
                .antMatchers("/myProducts").authenticated()
                .antMatchers("/addProduct").hasAnyAuthority("ADMIN","DEALER")
                .antMatchers("/pendingApproval").hasAuthority("ADMIN")
                .antMatchers("/myCart").authenticated()
                .antMatchers("/resources/**","/static/**","/css/**","/fonts/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/welcome")
                .failureUrl("/login?error")
                .and()
                .rememberMe().rememberMeParameter("my-remember-me")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(86400)//valid for one day
                .userDetailsService(userDetailsService())
                .and()
                .logout()
                .permitAll()
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login?logout");
    }

    //using database data for authentication
    @Bean
    DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsServiceImpl);//

        return  daoAuthenticationProvider;
    }

    //getting the harshed password
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }


}
