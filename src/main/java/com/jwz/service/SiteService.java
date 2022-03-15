package com.jwz.service;

import com.alibaba.fastjson.JSON;
import com.jwz.pojo.Site;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface SiteService {
    public List<Site> getAllSites();

    public Site getSite(String name);

    public void addSite(String name, String url, String cookie) throws IOException;

    public void deleteSite(String name) throws IOException;

    public void updateSite(String name, String newName, String url, String cookie) throws IOException;
}
