package cn.will.po;

import java.util.Objects;

/**
 * Created on 2018-01-13 1:03 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public class Album {
    private int id;

    private String title;

    private String cover;

    private int hot;

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
