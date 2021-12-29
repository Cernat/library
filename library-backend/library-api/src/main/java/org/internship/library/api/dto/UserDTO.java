package org.internship.library.api.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO
{

    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String userRole;
    private List<LinkBorrowDTO> linkBorrow = new ArrayList<>();


    public UserDTO(Integer id, String userName, String password, String email, String userRole, List<LinkBorrowDTO> linkBorrow) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
        this.linkBorrow = linkBorrow;
    }

    public UserDTO()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
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

    public String getUserRole()
    {
        return userRole;
    }

    public void setUserRole(String userRole)
    {
        this.userRole = userRole;
    }

    public List<LinkBorrowDTO> getLinkBorrow() {
        return linkBorrow;
    }

    public void setLinkBorrow(List<LinkBorrowDTO> linkBorrow) {
        this.linkBorrow = linkBorrow;
    }
}
