package org.internship.library.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.filter.CorsFilter;

import static org.internship.library.app.security.UserRole.ADMIN;
import static org.internship.library.app.security.UserRole.USER;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ApplicationPasswordEncoder applicationPasswordEncoder;
    private final MyUserDetailsService myUserDetailsService;
    private final CorsFilter corsFilter;

    @Autowired
    public SecurityConfiguration(ApplicationPasswordEncoder applicationPasswordEncoder,
                                 MyUserDetailsService myUserDetailsService,
                                 CorsFilter corsFilter) {
        this.applicationPasswordEncoder = applicationPasswordEncoder;
        this.myUserDetailsService = myUserDetailsService;
        this.corsFilter = corsFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(corsFilter, ChannelProcessingFilter.class);
        http.httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/registration/**").permitAll()
                .antMatchers("/swagger-ui/**").hasAuthority(ADMIN.name())
//                .antMatchers("/book/**").hasAuthority(ADMIN.name())
                .antMatchers("/book/**").hasAuthority(USER.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .rememberMe();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(applicationPasswordEncoder);
        return provider;
    }
}
