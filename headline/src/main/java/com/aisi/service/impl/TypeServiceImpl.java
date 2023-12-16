package com.aisi.service.impl;

import com.aisi.utils.Result;
import com.aisi.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aisi.entity.Type;
import com.aisi.service.TypeService;
import com.aisi.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author jianyu
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2023-12-14 15:28:57
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{
    @Autowired
    private TypeMapper mapper;

    /**
     * 查询所有新闻类型
     *
     * @return
     */
    @Override
    public Result findAllTypes() {
        List<Type> types = mapper.selectList(null);
        return Result.build(types, ResultCodeEnum.SUCCESS);
    }
}




