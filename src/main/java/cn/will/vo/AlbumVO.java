package cn.will.vo;

import java.util.List;

/**
 * Created on 2018-01-14 4:58 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public class AlbumVO {
    private String title;

    private String coverPic;

    private List<AlbumMusicVO> musics;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public List<AlbumMusicVO> getMusics() {
        return musics;
    }

    public void setMusics(List<AlbumMusicVO> musics) {
        this.musics = musics;
    }
}
