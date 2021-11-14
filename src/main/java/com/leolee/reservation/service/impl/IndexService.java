package com.leolee.reservation.service.impl;

import com.leolee.reservation.Dao.DepartmentRepository;
import com.leolee.reservation.Dao.ReservationRepository;
import com.leolee.reservation.entity.Department;
import com.leolee.reservation.entity.Reservation;
import com.leolee.reservation.entity.User;
import com.leolee.reservation.service.IndexServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName IndexService
 * @Description TODO
 * @Author liwen
 * @Date 2019/9/20 0:04
 * @Version 1.0
 **/
@Service
public class IndexService implements IndexServiceInterface {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public User getOnlineUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("reservationUserID")){
                if (session.getAttribute("user"+cookie.getValue()) != null){
                    User user = (User) session.getAttribute("user"+cookie.getValue());
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public void logoff(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }

    @Override
    public String reserve(HttpServletRequest request, Date reservationDate, String departmentName, String phone) {
        User user = getOnlineUser(request);
        //判断登录
        if (user == null)
            return "未登录";

        //判断日期
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(calendar.DATE, -1);
        Date begin = calendar.getTime();
        calendar.add(calendar.DATE, 3);
        Date deadline = calendar.getTime();
        if (reservationDate.compareTo(begin) < 0 && reservationDate.compareTo(deadline) > 0)
            return "不在预约时间内";

        //判断剩余
        Department department = departmentRepository.findDepartmentByName(departmentName);
        int maxNum = department.getMaxNum();
        List<Reservation> reservations = reservationRepository.findReservationsByDateAndReservationStatusAndDepartmentId(reservationDate, true, department.getId());
        int nowNum = reservations.size();
        if (nowNum >= maxNum)
            return "无剩余";

        //判断手机号
        List<Reservation> reservationsByPhone = reservationRepository.findReservationsByDateAndPhoneAndReservationStatus(begin, deadline, phone, true);
        if (reservationsByPhone.size() > 0)
            return "手机号已预约";

        //预约成功
        Reservation reservation = new Reservation(reservationDate, user.getId(), department.getId(), phone, true);
        reservationRepository.save(reservation);
        return "预约成功";
    }

    @Override
    public String cancel(HttpServletRequest request, String phone) {
        User user = getOnlineUser(request);
        //判断登录
        if (user == null)
            return "未登录";

        //判断是否预约
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(calendar.DATE, -1);
        Date begin = calendar.getTime();
        calendar.add(calendar.DATE, 3);
        Date deadline = calendar.getTime();
        List<Reservation> reservationsByPhone = reservationRepository.findReservationsByDateAndPhoneAndReservationStatus(begin, deadline, phone, true);
        if (reservationsByPhone.size() == 0)
            return "该手机号未预约";

        //取消预约
        Reservation reservation = reservationsByPhone.get(0);
        System.out.println(reservation.getId());
        reservationRepository.updateReservationById(false, reservation.getId());
        return "取消成功";
    }
}
