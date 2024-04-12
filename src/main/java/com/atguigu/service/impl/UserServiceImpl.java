package com.atguigu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author convertlab
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-04-10 22:37:41
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 登录业务
     * 1。根据账号，查询用户对象 - loginUser
     * 2。如果查询对象为null，查询失败，账号错误，501
     * 3。对比密码，如果失败，返回503
     * 4。账号密码都正确，生成一个token并返回
     *
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(userLambdaQueryWrapper);

        if (loginUser == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        if (!StringUtils.isEmpty(user.getUserPwd())
            && MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())) {
            //登录成功
            //根据用户id生成token
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            //将token封装到result
            Map map = new HashMap();
            map.put("token", token);
            return Result.ok(map);

        }

        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }


    /**
     * 1。token是否过期
     * 2。根据token解析userId
     * 3。根据用户id查询数据
     * 4。去掉密码，封装result结果返回即可
     *
     * @param token
     * @return
     */
    @Override
    public Result getUserInfo(String token) {
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        int userId = jwtHelper.getUserId(token).intValue();

        User user = userMapper.selectById(userId);
        user.setUserPwd("");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("loginUser", user);

        return Result.ok(map);
    }

    @Override
    public Result checkUserName(String userName) {
        System.out.println(userName);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, userName);

        User user = userMapper.selectOne(lambdaQueryWrapper);
        System.out.println("user = " + user);

        if (user == null) {
            return Result.ok(null);
        }

        return Result.build(null, ResultCodeEnum.USERNAME_USED);
    }

    /**
     * 1。依然检查账号是否已经被注册
     * 2。密码加密处理
     * 3。账号数据保存
     * 4。返回结果
     *
     * @param user
     * @return
     */
    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());

        User userInMysql = userMapper.selectOne(lambdaQueryWrapper);

        if (userInMysql != null) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));

        userMapper.insert(user);

        return Result.ok(null);
    }
}




