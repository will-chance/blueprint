package cn.will.mapper;

import cn.will.po.Artist;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created on 2018-01-13 1:02 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public interface ArtistMapper {
    int insertBatch(@Param("artists") List<Artist> artists);
}
