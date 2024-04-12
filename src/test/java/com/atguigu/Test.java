package com.atguigu;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @ClassName: Test
 * @Author: bin.zhao
 * @Description:
 * @Date: Created in 21:07 2024/04/11
 * @Modified By: bin.zhao
 * @Modify Time: 21:07 2024/04/11
 * @Version: 1.0
 */
@SpringBootTest
public class Test {
    @Autowired
    private UserMapper userMapper;

    @org.junit.jupiter.api.Test
    public void test() {
        String username = "lisi2";

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        System.out.println("user = " + user);
    }

    @org.junit.jupiter.api.Test
    public void test2(){
        Date date = new Date();
        System.out.println("date = " + date);
    }

}
