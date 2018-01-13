package cn.will.mapper;

import cn.will.po.Music;

import java.util.List;

/**
 * Created on 2018-01-13 1:00 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public interface MusicMapper {
    List<Music> listNewestMusic();
}
