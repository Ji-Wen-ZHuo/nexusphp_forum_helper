package com.jwz.controller;

import com.alibaba.fastjson.JSON;
import com.jwz.layui.LayuiJSON;
import com.jwz.network.Crawler;
import com.jwz.pojo.Site;
import com.jwz.pojo.Topic;
import com.jwz.service.SiteServiceImplJSON;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class TopicController {
    @RequestMapping(value = "/topics/{name}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public String getTopics(@PathVariable String name) throws IOException {
        SiteServiceImplJSON siteService = new SiteServiceImplJSON();
        Site site = siteService.getSite(name);
        if(site == null) return "";
        Crawler crawler = new Crawler();
        List<Topic> topics = crawler.getTopics(site);
        return LayuiJSON.addHeader(JSON.toJSONString(topics));
    }
}
