package com.atguigu.service;

import com.atguigu.pojo.Type;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author convertlab
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-04-10 22:37:41
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();

    Result showHeadlineDetail(String hid);
}
