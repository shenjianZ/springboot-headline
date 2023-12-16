package com.aisi.controller;

import com.aisi.entity.Headline;
import com.aisi.service.HeadlineService;
import com.aisi.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class headlineController {
    @Autowired
    private HeadlineService service;

    /**
     * 发布新闻接口实现
     * @param headline JSON类型
     * @param token   Header类型
     * @return
     */
    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token){
        Result result = service.publish(headline,token);
        return result;
    }

    /**
     * 头条修改回显接口
     * @param hid
     * @return
     */
    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid){
        Headline headline = service.getById(hid);
        Map data = new HashMap<>();
        data.put("headline",headline);
        return Result.ok(data);
    }

    /**
     * 头条修改接口
     * @param headline
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody Headline headline){
        Result result = service.updateData(headline);
        return result;
    }

    @PostMapping("removeByHid")
    public Result removeByHid(@RequestParam Integer hid){
        service.removeById(hid);
        return Result.ok(null);
    }


}
