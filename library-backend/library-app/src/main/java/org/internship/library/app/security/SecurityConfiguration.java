package org.internship.library.app.security;

import static org.internship.library.app.security.UserRole.ADMIN;
import static org.internship.library.app.security.UserRole.USER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{

    private final ApplicationPasswordEncoder applicationPasswordEncoder;
    private final MyUserDetailsService myUserDetailsService;
    private final CorsFilter corsFilter;

    @Autowired
    public SecurityConfiguration(ApplicationPasswordEncoder applicationPasswordEncoder,
        MyUserDetailsService myUserDetailsService,
        CorsFilter corsFilter)
    {
        this.applicationPasswordEncoder = applicationPasswordEncoder;
        this.myUserDetailsService = myUserDetailsService;
        this.corsFilter = corsFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.addFilterBefore(corsFilter, ChannelProcessingFilter.class);
        http.httpBasic().and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/v1/registration/**").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/api/v1/user/**").permitAll()
            .antMatchers("/book/**").permitAll()
            .anyRequest().authenticated();

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(applicationPasswordEncoder);
        return provider;
    }
}
