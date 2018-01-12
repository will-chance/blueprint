package cn.will;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created on 2018-01-11 11:11 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public class CollectMusic {

    private ObjectMapper mapper;

    @Before
    public void before(){
        mapper = new ObjectMapper();
    }

    @Test
    public void getFamiliarJsoup(){
        String targetURL = "https://music.163.com/#/playlist?id=44776942";
        try {
            Document document = Jsoup.connect(targetURL).get();
            Elements elements = document.select(".m-table");
//            Elements elements = document.getElementsByClass("m-table");
//            Elements elements = document.getElementsByTag("table");
            System.out.println(elements.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
