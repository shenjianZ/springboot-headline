package com.aisi.service;

import com.aisi.entity.Type;
import com.aisi.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author jianyu
* @description 针对表【news_type】的数据库操作Service
* @createDate 2023-12-14 15:28:57
*/
public interface TypeService extends IService<Type> {

    /**
     * 查询所有新闻类型
     * @return
     */
    Result findAllTypes();
}
