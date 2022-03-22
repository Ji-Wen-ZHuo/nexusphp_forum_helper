package com.jwz.controller;

import com.alibaba.fastjson.JSON;
import com.jwz.layui.LayuiJSON;
import com.jwz.pojo.Site;
import com.jwz.service.SiteService;
import com.jwz.service.SiteServiceImplSQL;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class SiteController {
    @RequestMapping(value = "/site", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getAllSites() {
        SiteService siteService = new SiteServiceImplSQL();
        List<Site> sites = siteService.getAllSites();
        return LayuiJSON.addHeader(JSON.toJSONString(sites));
    }

    @RequestMapping(value = "/site/{name}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getAllSites(@PathVariable String name) {
        SiteService siteService = new SiteServiceImplSQL();
        Site site = siteService.getSite(name);
        return LayuiJSON.addHeader(JSON.toJSONString(site));
    }

    @RequestMapping(value = "/site/{name}/{url}/{cookie}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String addSite(@PathVariable String name, @PathVariable String url, @PathVariable String cookie) {
        url = url.replace('-', '/');
        cookie = cookie.replace('@', ';');
        try {
            SiteService siteService = new SiteServiceImplSQL();
            siteService.addSite(name, url, cookie);
        } catch (IOException e) {
            return "fail!";
        }
        return "success";
    }

    @RequestMapping(value = "/site/{name}/{newname}/{url}/{cookie}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String updateSite(@PathVariable String name, @PathVariable String newname, @PathVariable String url,
                         @PathVariable String cookie) {
        try {
            SiteService siteService = new SiteServiceImplSQL();
            siteService.updateSite(name, newname, url, cookie);
        } catch (IOException e) {
            return "fail!";
        }
        return "success";
    }

    @RequestMapping(value = "/site/{name}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public String deleteSite(@PathVariable String name) {
        try {
            SiteService siteService = new SiteServiceImplSQL();
            siteService.deleteSite(name);
        } catch (IOException e) {
            return "fail!";
        }
        return "success";
    }

    @RequestMapping(value = "/site/backup", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String backup(HttpServletResponse response , HttpServletRequest request) throws IOException {
        String fileName = "config.json";

        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoder.encode(fileName, "UTF-8"));

        List<Site> sites = new SiteServiceImplSQL().getAllSites();
        String config = JSON.toJSONString(sites);
        StringReader reader = new StringReader(config);

        int buff;
        OutputStream out = response.getOutputStream();
        while((buff = reader.read())!= -1){
            out.write(buff);
            out.flush();
        }
        out.close();

        return null;
    }
}
