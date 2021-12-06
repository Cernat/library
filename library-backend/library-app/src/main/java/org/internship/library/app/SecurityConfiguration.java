package org.internship.library.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    private final ApplicationPasswordEncoder applicationPasswordEncoder;
    private final MyUserDetailsService myUserDetailsService;
    private final CorsFilter corsFilter;

    public SecurityConfiguration(ApplicationPasswordEncoder applicationPasswordEncoder, MyUserDetailsService myUserDetailsService, CorsFilter corsFilter) {
        this.applicationPasswordEncoder = applicationPasswordEncoder;
        this.myUserDetailsService = myUserDetailsService;
        this.corsFilter = corsFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Set your configuration on the auth object

        auth.userDetailsService(myUserDetailsService).passwordEncoder(encoder());
//        auth.authenticationProvider(authenticationProvider());

//        auth.inMemoryAuthentication()
//                .withUser("blah")
//                .password("blah")
//                .roles("USER")
//                .and()
//                .withUser("inMemoryTest")
//                .password("inMemoryTest")
//                .roles("ADMIN");
    }

//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.addFilterBefore(corsFilter, ChannelProcessingFilter.class);
        http.authorizeRequests()
//                .antMatchers("/book").permitAll()
//                .antMatchers("/**").hasAnyRole("ADMIN")
                .antMatchers("/").hasAnyRole("ADMIN")
//                .antMatchers("/book").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
//        provider.setPasswordEncoder(applicationPasswordEncoder);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }
}
