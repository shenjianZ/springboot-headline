package com.aisi.service.impl;

import com.aisi.entity.vo.PortalVo;
import com.aisi.utils.JwtHelper;
import com.aisi.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aisi.entity.Headline;
import com.aisi.service.HeadlineService;
import com.aisi.mapper.HeadlineMapper;
import lombok.Data;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author jianyu
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2023-12-14 15:28:57
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{
    @Autowired
    private HeadlineMapper mapper;

    @Autowired
    private JwtHelper jwtHelper;
    /**
     * 分页查询接口实现
     *
     * @param portalVo 请求体参数 JSON类型
     * @return
     */
    @Override
    public Result findNewsPage(PortalVo portalVo) {
        IPage<Map> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        mapper.selectAllPage(page,portalVo);
        List<Map> records = page.getRecords();
        Map data= new HashMap();
        data.put("pageData",records);
        data.put("pageNum",page.getCurrent());
        data.put("pageSize",page.getSize());
        data.put("totalPage",page.getPages());
        data.put("totalSize",page.getTotal());
        Map pageInfo= new HashMap<>();
        pageInfo.put("pageInfo",data);
        return Result.ok(pageInfo);
    }

    /**
     * 查询头条详情接口实现
     *
     * @param hid
     * @return
     */
    @Override
    public Result showHeadlineDetail(Integer hid) {
        // 查询原始数据
        Map headLineDetail = mapper.queryHeadlineDetail(hid);

        // 构造更新条件
        LambdaUpdateWrapper<Headline> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(Headline::getHid, hid)
                .eq(Headline::getVersion, (Integer) headLineDetail.get("version"));

        // 构造更新字段
        Headline updateEntity = new Headline();
        updateEntity.setPageViews((Integer) headLineDetail.get("pageViews") + 1);
        updateEntity.setVersion((Integer) headLineDetail.get("version") + 1);

        // 执行更新
        boolean updateResult = mapper.update(updateEntity, updateWrapper) > 0;

        // 返回结果
            Map data = new HashMap();
            data.put("headline", headLineDetail);
            return Result.ok(data);
    }

    /**
     * 发布新闻接口实现
     *
     * @param headline
     * @param token
     * @return
     */
    @Override
    public Result publish(Headline headline, String token) {
        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        mapper.insert(headline);
        return Result.ok(null);
    }

    /**
     * 头条修改接口
     *
     * @param headline
     * @return
     */
    @Override
    public Result updateData(Headline headline){
        Headline headline1 = mapper.selectById(headline);
        LambdaUpdateWrapper<Headline> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(Headline::getHid, headline1.getHid())
                .eq(Headline::getVersion, (Integer) headline1.getVersion());
        Headline updateEntity = new Headline();
        updateEntity.setUpdateTime(new Date());
        updateEntity.setVersion((Integer) headline1.getVersion() + 1);
        mapper.update(updateEntity,updateWrapper);
        return Result.ok(null);
    }


}




