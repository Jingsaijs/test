package com.leolee.reservation.controller;

import com.alibaba.fastjson.JSONObject;
import com.leolee.reservation.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author liwen
 * @Date 2019/9/20 0:02
 * @Version 1.0
 **/

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("login")
    public JSONObject login(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject param){
        JSONObject jsonObject = new JSONObject();
//        System.out.println(param.toJSONString());
        int id = Integer.parseInt(param.get("id").toString());
        String password = param.get("password").toString();
        boolean isRemembered;
        try {
            param.get("remember");
            isRemembered = true;
        } catch (Exception e) {
            isRemembered = false;
        }
        if (loginService.login(request, response, id, password, isRemembered))
            jsonObject.put("result", "success");
        else
            jsonObject.put("result", "fail");
        return jsonObject;
    }

    @PostMapping("regist")
    public JSONObject regist(@RequestBody JSONObject param) {
        JSONObject jsonObject = new JSONObject();
        String name = param.get("name").toString();
        String password = param.get("password").toString();
        jsonObject.put("result", loginService.regist(name, password));
        return jsonObject;
    }

}
