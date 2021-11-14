package com.leolee.reservation.entity;

import javax.persistence.*;

/**
 * @ClassName Phone
 * @Description TODO
 * @Author liwen
 * @Date 2019/9/20 13:58
 * @Version 1.0
 **/
@Entity
@Table(name = "Phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String phone;

    @Column
    boolean isUsed;

    public Phone() {
    }

    public Phone(String phone, boolean isUsed) {
        this.phone = phone;
        this.isUsed = isUsed;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phone='" + phone + '\'' +
                ", isUsed=" + isUsed +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
