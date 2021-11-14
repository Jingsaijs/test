package com.leolee.reservation.service.impl;

import com.leolee.reservation.Dao.UserRepository;
import com.leolee.reservation.entity.User;
import com.leolee.reservation.service.LoginServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginService
 * @Description TODO
 * @Author liwen
 * @Date 2019/9/20 0:05
 * @Version 1.0
 **/
@Service
public class LoginService implements LoginServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean login(HttpServletRequest request, HttpServletResponse response, int id, String password, boolean isRemembered) {
        User user = userRepository.findUser(id, password);
        if (user == null){
            return false;
        }

        Cookie cookie = new Cookie("reservationUserID", id + "");
        cookie.setMaxAge(30 * 24 * 3600);
        cookie.setPath("/");
        response.addCookie(cookie);

        HttpSession session = request.getSession();
        session.setAttribute("user"+id, user);
        session.setMaxInactiveInterval(2 * 3600);

        if (isRemembered) {
            Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
            sessionCookie.setMaxAge(2 * 3600);
            sessionCookie.setPath("/");
            response.addCookie(sessionCookie);
        }

        response.addCookie(cookie);

        return true;
    }

    @Override
    public int regist(String name, String password) {
        User user = new User(password, name);
        userRepository.save(user);
        return user.getId();
    }
}
