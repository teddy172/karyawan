package com.binar.grab2.Dao;

import lombok.Data;

@Data
public class RegisterModel {
    public Long id;

    public String email;

    public String username;

    public String password;

    public String fullname;
}
