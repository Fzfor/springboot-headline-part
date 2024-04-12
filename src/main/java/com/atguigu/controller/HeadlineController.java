package com.atguigu.controller;

import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: HeadlineController
 * @Author: bin.zhao
 * @Description:
 * @Date: Created in 08:33 2024/04/12
 * @Modified By: bin.zhao
 * @Modify Time: 08:33 2024/04/12
 * @Version: 1.0
 */

@RestController
@CrossOrigin
@RequestMapping("headline")
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;

    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token) {
        Result result = headlineService.publish(headline, token);
        return result;
    }

    @PostMapping("/findHeadlineByHid")
    public Result findHeadlineByHid(String hid) {
        Headline headline = headlineService.getById(hid);
        Map map = new HashMap();
        map.put("headline", headline);

        return Result.ok(map);

    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline){
        Result result = headlineService.updateData(headline);

        return result;
    }

    @PostMapping("removeByHid")
    public Result removeByHid(String hid){
        headlineService.removeById(hid);

        return Result.ok(null);
    }
}
