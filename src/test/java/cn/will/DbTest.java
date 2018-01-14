package cn.will;

import cn.will.mapper.AlbumMapper;
import cn.will.mapper.UserMapper;
import cn.will.po.User;
import cn.will.vo.AlbumVO;
import com.alibaba.fastjson.JSONObject;
import fxml.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created on 2018-01-12 11:20 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class DbTest {

    @Autowired private UserMapper userMapper;
    @Autowired private AlbumMapper albumMapper;

    @Test
    public void testMapper(){
        User user = new User();
        user.setUsername("USERNAME");
        user.setPassword("password");
        userMapper.insert(user);
    }

    @Test
    public void testListAlbumMusicByArtistId(){
        List<AlbumVO> albums = albumMapper.listAlbumMusicByArtistId(1497,null);
        System.out.println(JSONObject.toJSONString(albums));
    }
}
