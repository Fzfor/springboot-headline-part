package com.atguigu.service.impl;

import com.atguigu.mapper.HeadlineMapper;
import com.atguigu.pojo.Headline;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.Type;
import com.atguigu.service.TypeService;
import com.atguigu.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author convertlab
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-04-10 22:37:41
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private HeadlineMapper  headlineMapper;

    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);
        return Result.ok(types);
    }

    @Override
    public Result showHeadlineDetail(String hid) {
        Map map = headlineMapper.selectDetailMap(hid);

        Map data = new HashMap();
        data.put("headline", map);

        //修改pageviews
        Headline headline = new Headline();
        headline.setHid((Integer) map.get("hid"));
        headline.setPageViews((Integer) map.get("pageViews") + 1);
        headline.setVersion((Integer) map.get("version"));
        headlineMapper.updateById(headline);


        return Result.ok(data);
    }
}




