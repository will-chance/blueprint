package cn.will.service;

import cn.will.mapper.AlbumMapper;
import cn.will.po.Album;
import cn.will.po.Music;
import cn.will.vo.AlbumVO;
import fxml.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2018-01-13 1:08 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Service
public class AlbumService {
    @Autowired private AlbumMapper albumMapper;

    public List<Music> listMusic(Album album){
        return null;
    }

    public List<AlbumVO> listArtistMusic(int artistId){
        return albumMapper.listAlbumMusicByArtistId(artistId, Main.getCurrentUser());
    }
}
