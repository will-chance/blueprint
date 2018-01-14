package cn.will.mapper;

import cn.will.po.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created on 2018-01-12 11:06 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public interface UserMapper {
    int insert(User user);

    User selectByUsernameAndPassword(User user);

    int insertUserFavoriteMusic(@Param("musicId") int musicId, @Param("user") User user);

    int deleteUserFavoriteMusic(@Param("musicId") int musicId, @Param("user") User user);
}
