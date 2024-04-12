package com.atguigu.controller;

import com.atguigu.pojo.Type;
import com.atguigu.pojo.vo.PortalVo;
import com.atguigu.service.HeadlineService;
import com.atguigu.service.TypeService;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: PortalController
 * @Author: bin.zhao
 * @Description:
 * @Date: Created in 21:38 2024/04/11
 * @Modified By: bin.zhao
 * @Modify Time: 21:38 2024/04/11
 * @Version: 1.0
 */

@RestController
@CrossOrigin
@RequestMapping("portal")
public class PortalController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result result = typeService.findAllTypes();
        return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }


    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(String hid){
        Result result = typeService.showHeadlineDetail(hid);
        return result;
    }

}
