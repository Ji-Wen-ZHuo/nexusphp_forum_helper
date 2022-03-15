package com.jwz.service;

import com.alibaba.fastjson.JSON;
import com.jwz.pojo.Site;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SiteServiceImplJSON implements SiteService{
    public List<Site> getAllSites() {
        List<Site> sites = new ArrayList<>();
        try {
            File file = new File("site.json");
            String fileString = FileUtils.readFileToString(file, "utf-8");
            sites = JSON.parseArray(fileString, Site.class);
        } catch (IOException e) {
            return sites;
        }
        return sites;
    }

    public Site getSite(String name) {
        List<Site> sites = getAllSites();
        for (Site site : sites) {
            if(name.equals(site.getName())) return site;
        }
        return null;
    }

    public void addSite(String name, String url, String cookie) throws IOException {
        List<Site> sites = getAllSites();
        for (Site site : sites) {
            if (site.getName().equals(name)) {
                System.out.println("站点名冲突");
                return;
            }
        }
        sites.add(new Site(name, url, cookie));
        String s = JSON.toJSONString(sites);
        File file = new File("site.json");
        FileUtils.writeStringToFile(file, s, "utf-8");
    }

    public void deleteSite(String name) throws IOException {
        List<Site> sites = getAllSites();
        for (int i = 0; i < sites.size(); i++) {
            if(sites.get(i).getName().equals(name)) {
                sites.remove(i);
//                System.out.println("删除成功");
            }
        }
        String s = JSON.toJSONString(sites);
        File file = new File("site.json");
        FileUtils.writeStringToFile(file, s, "utf-8");
    }

    public void updateSite(String name, String newName, String url, String cookie) throws IOException {
        List<Site> sites = getAllSites();
        for (int i = 0; i < sites.size(); i++) {
            if(sites.get(i).getName().equals(name)) {
                Site site = new Site(newName, url, cookie);
                sites.set(i, site);
//                System.out.println("修改成功");
            }
        }
        String s = JSON.toJSONString(sites);
        File file = new File("site.json");
        FileUtils.writeStringToFile(file, s, "utf-8");
    }
}
