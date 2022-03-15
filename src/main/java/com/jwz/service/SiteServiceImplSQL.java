package com.jwz.service;

import com.jwz.mapper.SiteMapper;
import com.jwz.pojo.Site;
import com.jwz.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SiteServiceImplSQL implements SiteService{
    @Override
    public List<Site> getAllSites() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SiteMapper mapper = sqlSession.getMapper(SiteMapper.class);
        List<Site> sites = mapper.getSites();
        sqlSession.close();
        return sites;
    }

    @Override
    public Site getSite(String name) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SiteMapper mapper = sqlSession.getMapper(SiteMapper.class);
        Site site = mapper.getSite(name);
        sqlSession.close();
        return site;
    }

    @Override
    public void addSite(String name, String url, String cookie) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SiteMapper mapper = sqlSession.getMapper(SiteMapper.class);
        mapper.insertSite(new Site(name, url, cookie));
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteSite(String name) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SiteMapper mapper = sqlSession.getMapper(SiteMapper.class);
        mapper.deleteSite(name);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateSite(String name, String newName, String url, String cookie) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SiteMapper mapper = sqlSession.getMapper(SiteMapper.class);
        mapper.updateSite(new Site(name, url, cookie));
        sqlSession.commit();
        sqlSession.close();
    }
}
