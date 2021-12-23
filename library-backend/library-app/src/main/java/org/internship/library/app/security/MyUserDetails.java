package org.internship.library.app.security;

import java.util.Collection;
import java.util.Collections;

import org.internship.library.app.persistence.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails
{

    private final UserEntity userEntity;

    public MyUserDetails(UserEntity userEntity)
    {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getUserRole().name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword()
    {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername()
    {
        return userEntity.getUserName();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
