package com.atguigu;

import com.atguigu.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: JwtTest
 * @Author: bin.zhao
 * @Description:
 * @Date: Created in 09:06 2024/04/11
 * @Modified By: bin.zhao
 * @Modify Time: 09:06 2024/04/11
 * @Version: 1.0
 */

@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtHelper jwtHelper;

    @Test
    public void test(){
        //生成token 传入用户标识
        String token = jwtHelper.createToken(1L);
        System.out.println("token = " + token);

        //解析用户标识
        Long userId = jwtHelper.getUserId(token);
        System.out.println("userId = " + userId);

        //校验是否到期
        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println("expiration = " + expiration);

    }

    @Test
    public void test2(){


        System.out.println();
    }
}
