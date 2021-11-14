package com.leolee.reservation.entity;

import javax.persistence.*;

/**
 * @ClassName Department
 * @Description TODO
 * @Author liwen
 * @Date 2019/9/20 0:12
 * @Version 1.0
 **/

@Entity
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 10)
    String name;

    @Column(length = 4)
    int maxNum;


    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxNum=" + maxNum +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public Department(String name, int maxNum) {
        this.name = name;
        this.maxNum = maxNum;
    }

    public Department() {
    }
}
