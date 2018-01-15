package cn.will.mapper;

import cn.will.po.User;
import cn.will.vo.MusicResultVO;
import cn.will.vo.PlaylistVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    int insertPurchaseMusic(@Param("musicId") int musicId,@Param("user") User user);

    List<MusicResultVO> listFavoriteMusicByUser(@Param("user") User user);

    List<MusicResultVO> listPurchaseMusicByUser(@Param("user") User user);

    int insertPlaylist(@Param("title") String title,@Param("user") User user);

    List<PlaylistVO> listUserCreatePlaylist(@Param("user")User user);

    List<PlaylistVO> listUserFavoritePlaylist(@Param("user")User user);
}
