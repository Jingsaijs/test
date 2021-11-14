package com.leolee.reservation.service;

import com.leolee.reservation.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface IndexServiceInterface {

    public User getOnlineUser(HttpServletRequest request);

    public void logoff(HttpServletRequest request);

    public String reserve(HttpServletRequest request, Date reservationDate, String departmentName, String phone);

    public String cancel(HttpServletRequest request, String phone);
}
