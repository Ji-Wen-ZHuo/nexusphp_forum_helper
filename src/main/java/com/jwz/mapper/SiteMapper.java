package com.jwz.mapper;

import com.jwz.pojo.Site;

import java.util.List;

public interface SiteMapper {
    public List<Site> getSites();
    public Site getSite(String name);
    public void insertSite(Site site);
    public void deleteSite(String name);
    public void updateSite(Site site);
}
