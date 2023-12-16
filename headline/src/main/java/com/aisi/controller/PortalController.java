package com.aisi.controller;

import com.aisi.entity.vo.PortalVo;
import com.aisi.service.HeadlineService;
import com.aisi.service.TypeService;
import com.aisi.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/portal")
@RestController
@CrossOrigin
public class PortalController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private HeadlineService headlineService;

    /**
     *  查询所有新闻类型
     * @return   返回值为 JSON类型
     */
    @RequestMapping("/findAllTypes")
    public Result findAllTypes(){
        Result result = typeService.findAllTypes();
        return result;
    }

    /**
     * 分页查询接口实现
     * @param portalVo 请求参数为 JSON 类型
     * @return  返回值为 JSON类型
     */
    @PostMapping("/findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }

    /**
     * 检验登录
     * @param hid
     * @return
     */
    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }

}
