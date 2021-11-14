package com.leolee.reservation.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Reservation
 * @Description TODO
 * @Author liwen
 * @Date 2019/9/20 0:09
 * @Version 1.0
 **/

@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Temporal(TemporalType.DATE)
    @Column
    Date date;

    @Column(length = 10)
    int userId;

    @Column(length = 10)
    int departmentId;

    @Column(length = 11)
    String phone;

    @Column(length = 1)
    boolean reservationStatus;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", date=" + date +
                ", userId=" + userId +
                ", departmentId=" + departmentId +
                ", phone='" + phone + '\'' +
                ", reservationStatus=" + reservationStatus +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(boolean reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Reservation(Date date, int userId, int departmentId, String phone, boolean reservationStatus) {
        this.date = date;
        this.userId = userId;
        this.departmentId = departmentId;
        this.phone = phone;
        this.reservationStatus = reservationStatus;
    }

    public Reservation() {
    }
}
