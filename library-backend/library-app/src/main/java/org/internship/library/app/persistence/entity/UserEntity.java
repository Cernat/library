package org.internship.library.app.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.internship.library.app.security.UserRole;

import io.swagger.annotations.ApiParam;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "user_name", unique = true)
    @ApiParam(value = "test")
    private String userName;

    @Column(name = "password")
    @ApiParam(value = "test")
    private String password;

    @Column(name = "email")
    @ApiParam(value = "test")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "app_user_role")
    @ApiParam(value = "USER")
    private UserRole userRole;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<LinkBorrowEntity> linkBorrow;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public UserRole getUserRole()
    {
        return userRole;
    }

    public void setUserRole(UserRole userRole)
    {
        this.userRole = userRole;
    }

    public Integer getId()
    {
        return Id;
    }

    public void setId(Integer id)
    {
        Id = id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<LinkBorrowEntity> getLinkBorrow()
    {
        return linkBorrow;
    }

    public void setLinkBorrow(List<LinkBorrowEntity> linkBorrow)
    {
        this.linkBorrow = linkBorrow;
    }
}
