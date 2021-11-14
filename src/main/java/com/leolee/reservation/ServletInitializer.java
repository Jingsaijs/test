package com.leolee.reservation;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @ClassName ServletInitializer
 * @Description TODO
 * @Author liwen
 * @Date 2019/9/22 18:17
 * @Version 1.0
 **/

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ReservationApplication.class);
    }
}
