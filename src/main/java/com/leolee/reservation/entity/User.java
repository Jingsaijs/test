package com.leolee.reservation.entity;

import javax.persistence.*;

/**
 * @ClassName User
 * @Description TODO
 * @Author liwen
 * @Date 2019/9/20 0:07
 * @Version 1.0
 **/

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 20)
    String password;

    @Column(length = 20)
    String name;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(String password, String name) {
        this.password = password;
        this.name = name;
    }
}
