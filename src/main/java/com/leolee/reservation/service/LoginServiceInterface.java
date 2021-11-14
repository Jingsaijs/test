package com.leolee.reservation.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginServiceInterface {

    public boolean login(HttpServletRequest request, HttpServletResponse response, int id, String password, boolean isRemembered);

    public int regist(String name, String password);
}
