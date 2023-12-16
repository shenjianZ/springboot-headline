package com.aisi.mapper;

import com.aisi.entity.Headline;
import com.aisi.entity.vo.PortalVo;
import com.aisi.utils.Result;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author jianyu
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2023-12-14 15:28:57
* @Entity com.aisi.entity.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectAllPage(IPage page, @Param("portalVo") PortalVo portalVo);


    Map queryHeadlineDetail(Integer hid );

}




