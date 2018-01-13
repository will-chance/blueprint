package cn.will.service;

import cn.will.mapper.MusicMapper;
import cn.will.po.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2018-01-13 1:09 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Service
public class MusicService {

    @Autowired
    private MusicMapper musicMapper;

    /**
     * 获取音乐详情
     * @param music
     * @return
     */
    public Music detailMusic(Music music){
        return null;
    }

    /**
     * 搜索音乐
     * @param keyword
     * @return
     */
    public List<Music> searchMusics(String keyword){
        return musicMapper.searchMusicByTitle(keyword);
    }

    public List<Music> listNewest(){
        return musicMapper.listNewestMusic();
    }
}
