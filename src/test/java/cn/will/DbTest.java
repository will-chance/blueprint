package cn.will;

import cn.will.mapper.UserMapper;
import cn.will.po.User;
import fxml.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testMapper(){
        User user = new User();
        user.setUsername("USERNAME");
        user.setPassword("password");
        userMapper.insert(user);
    }
}
