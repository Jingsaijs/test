package com.leolee.reservation.controller;

import com.alibaba.fastjson.JSONObject;
import com.leolee.reservation.entity.Department;
import com.leolee.reservation.entity.User;
import com.leolee.reservation.service.impl.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author liwen
 * @Date 2019/9/20 0:02
 * @Version 1.0
 **/

@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    IndexService indexService;

    @GetMapping("getOnlineUser")
    public JSONObject getOnlineUser(HttpServletRequest request){

        User  user = indexService.getOnlineUser(request);
        String name = user.getName();

        JSONObject obj = new JSONObject();
        obj.put("name", name);

        return obj;
    }

    @GetMapping("logoff")
    public void logoff(HttpServletRequest request){
        indexService.logoff(request);
    }

    @PostMapping("reserve")
    public JSONObject reserve(HttpServletRequest request, @RequestBody JSONObject param) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date reservationDate = sdf.parse(param.get("reservationDate").toString());
            String department = param.get("department").toString();
            String phone = param.get("reservationPhone").toString();
            System.out.println(reservationDate);
            System.out.println(department);
            System.out.println(phone);

            JSONObject obj = new JSONObject();
            obj.put("result", indexService.reserve(request, reservationDate, department, phone));
            return obj;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("cancel")
    public JSONObject cancel(HttpServletRequest request, @RequestBody JSONObject param) {
        String phone = param.get("phone").toString();
        System.out.println(phone);
        JSONObject obj = new JSONObject();
        obj.put("result", indexService.cancel(request, phone));
        return obj;
    }

}
