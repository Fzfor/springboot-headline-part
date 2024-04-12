package com.atguigu.interceptor;

import ch.qos.logback.core.net.ObjectWriter;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @ClassName: LoginProtectInterceptor
 * @Author: bin.zhao
 * @Description:
 * @Date: Created in 08:42 2024/04/12
 * @Modified By: bin.zhao
 * @Modify Time: 08:42 2024/04/12
 * @Version: 1.0
 */

@Component
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        boolean expiration = jwtHelper.isExpiration(token);
        if (!expiration) {
            return true;
        }

        Result<Object> result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);


        response.getWriter().println(json);
        //response.getWriter().write(json);

        return false;
    }
}
