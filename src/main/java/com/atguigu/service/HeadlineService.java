package com.atguigu.service;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.vo.PortalVo;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author convertlab
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-04-10 22:37:41
*/
public interface HeadlineService extends IService<Headline> {

    /**
     * 首页数据查询
     * @param portalVo
     * @return
     */
    Result findNewsPage(PortalVo portalVo);

    Result publish(Headline headline, String token);

    Result updateData(Headline headline);
}
