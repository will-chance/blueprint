package cn.will.mapper;

import cn.will.po.AlbumMusic;
import cn.will.po.Music;
import cn.will.po.User;
import cn.will.vo.MusicResultVO;
import cn.will.vo.PlaylistVO;
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

    int insertBatchMusicAlbum(List<AlbumMusic> list);

    List<MusicResultVO> listNewestMusic();

    List<MusicResultVO> searchMusicByTitle(@Param("keyword") String keyword,
                                           @Param("user")User user);

    List<MusicResultVO> searchMusicByArtist(@Param("keyword")String keyword,
                                            @Param("user")User user);

    List<MusicResultVO> searchMusicByAlbum(@Param("keyword")String keyword,
                                            @Param("user")User user);

    List<MusicResultVO> searchMusicByPlaylist(@Param("keyword")String keyword,
                                            @Param("user")User user);

    int insertPlaylistMusic(@Param("musicId")int musicId,@Param("playlistId")int playlistId);

    List<MusicResultVO> listPlaylistMusicById(@Param("playlistId") int playlistId,@Param("user")User user);

    PlaylistVO selectByUniqueKey(@Param("musicId") int musicId,@Param("playlistId") int playlistId);
}
