package cn.will;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
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

    private String TOP_LIST_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=20&offset=0&format=json";

    @Before
    public void before(){
        mapper = new ObjectMapper();
    }

    @Test
    public void crawl(){
        File data = new File("./data.json");
        try {
            System.out.println(mapper.readTree(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
