package com.aisi.service;

import com.aisi.entity.Headline;
import com.aisi.entity.vo.PortalVo;
import com.aisi.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author jianyu
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2023-12-14 15:28:57
*/
public interface HeadlineService extends IService<Headline> {

    /**
     * 分页查询接口实现
     * @param portalVo
     * @return
     */
    Result findNewsPage(PortalVo portalVo);

    /**
     *  查询头条详情接口实现
     * @param hid
     * @return
     */
    Result showHeadlineDetail(Integer hid);

    /**
     *  发布新闻接口实现
     * @param headline
     * @param token
     * @return
     */
    Result publish(Headline headline, String token);


    /**
     * 头条修改接口
     * @param headline
     * @return
     */
    Result updateData(Headline headline);
}
