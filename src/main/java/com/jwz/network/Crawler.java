package com.jwz.network;

import com.jwz.pojo.Site;
import com.jwz.pojo.Topic;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crawler {
    public List<Topic> getTopics(Site site) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(site.getUrl() + "?action=viewunread");
        httpGet.addHeader("Cookie", site.getCookie());
        CloseableHttpResponse response = httpclient.execute(httpGet);
        List<Topic> topics = new ArrayList<>();
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            Document document = Jsoup.parse(content);
            Elements elements = document.getElementsByClass("rowfollow");
            for (int i = 0; i < elements.size(); i += 2) {
                String title     = elements.get(i).child(0).child(0).child(0).child(1).child(0).text();
                String titleURL  = site.getUrl() + elements.get(i).child(0).child(0).child(0).child(1).child(0).attr(
                        "href");
                String modulo    = elements.get(i + 1).child(0).child(0).text();
                String moduloURL = site.getUrl() + elements.get(i + 1).child(0).attr("href");
                topics.add(new Topic(title, titleURL, modulo, moduloURL, site));
            }
        }
        return topics;
    }
}
