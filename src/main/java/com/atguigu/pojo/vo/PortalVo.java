package com.atguigu.pojo.vo;

import lombok.Data;

/**
 * @ClassName: PortalVo
 * @Author: bin.zhao
 * @Description:
 * @Date: Created in 21:56 2024/04/11
 * @Modified By: bin.zhao
 * @Modify Time: 21:56 2024/04/11
 * @Version: 1.0
 */
@Data
public class PortalVo {

    private String keyWords;

    private int type = 0;

    private int pageNum = 1;

    private int pageSize = 10;
}
