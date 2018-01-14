package cn.will.service;

import cn.will.mapper.UserMapper;
import cn.will.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018-01-12 11:49 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(User user){
        return userMapper.selectByUsernameAndPassword(user);
    }

    public boolean favoriteMusic(int musicId,User user){
        if (userMapper.insertUserFavoriteMusic(musicId,user) <= 0) return false;
        return true;
    }

    public boolean cancelFavoriteMusic(int musicId,User user){
        if (userMapper.deleteUserFavoriteMusic(musicId,user) <=0) return false;
        return true;
    }
}
