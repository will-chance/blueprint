package cn.will.mapper;

import cn.will.po.AlbumMusic;
import cn.will.po.Music;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created on 2018-01-13 1:00 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public interface MusicMapper {
    int insertBatch(@Param("musics") List<Music> musics);

    List<Music> listNewestMusic();

    List<Music> searchMusicByTitle(@Param("keyword") String keyword);

    int insertBatchMusicAlbum(List<AlbumMusic> list);
}
