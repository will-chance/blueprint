package cn.will.mapper;

import cn.will.po.Album;
import cn.will.po.User;
import cn.will.vo.AlbumVO;
import cn.will.vo.MusicResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created on 2018-01-13 1:00 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public interface AlbumMapper {
    int insertBatch(@Param("albums") List<Album> albums);

    Album selectByPrimaryKey(Integer id);

    List<AlbumVO> listAlbumMusicByArtistId(@Param("artistId") int artistId,@Param("user") User user);

    List<MusicResultVO> listAlbumMusicByAlbumId(@Param("albumId") int albumId,@Param("user")User user);
}
