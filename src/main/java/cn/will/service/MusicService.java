package cn.will.service;

import cn.will.mapper.MusicMapper;
import cn.will.po.Music;
import cn.will.vo.MusicResultVO;
import fxml.Main;
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
     * 根据歌名搜索音乐
     * @param keyword
     * @return
     */
    public List<MusicResultVO> searchMusicByTitle(String keyword){
        return musicMapper.searchMusicByTitle(keyword, Main.getCurrentUser());
    }

    public List<MusicResultVO> searchMusicByArtist(String keyword){
        return musicMapper.searchMusicByArtist(keyword, Main.getCurrentUser());
    }

    public List<MusicResultVO> searchMusicByAlbum(String keyword){
        return musicMapper.searchMusicByAlbum(keyword, Main.getCurrentUser());
    }

    public List<MusicResultVO> searchMusicByPlaylist(String keyword){
        return musicMapper.searchMusicByPlaylist(keyword, Main.getCurrentUser());
    }

    public List<MusicResultVO> listNewest(){
        return musicMapper.listNewestMusic();
    }

    /**
     * 购买音乐
     * @param musicId
     * @return
     */
    public boolean puchaseMusic(int musicId){
        return false;
    }

    public boolean addMusic2Playlist(int musicId,int playlistId){
        if (musicMapper.selectByUniqueKey(musicId,playlistId)!= null) return false;
        return musicMapper.insertPlaylistMusic(musicId,playlistId) >0;
    }

    public List<MusicResultVO> listPlaylistMusicById(int playlistId){
        return musicMapper.listPlaylistMusicById(playlistId,Main.getCurrentUser());
    }
}
