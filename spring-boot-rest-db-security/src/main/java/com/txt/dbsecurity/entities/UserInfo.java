package com.txt.dbsecurity.entities;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "userinfo")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "country")
    private String country;

    @Column(name = "enabled")
    private short enabled;

}
