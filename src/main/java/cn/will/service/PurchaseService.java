package cn.will.service;

import cn.will.po.Music;
import cn.will.po.User;
import org.springframework.stereotype.Service;

/**
 * Created on 2018-01-13 1:06 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Service
public class PurchaseService {

    /**
     * 购买音乐
     * @param music
     * @param user
     * @return
     */
    public boolean purchaseMusic(Music music,User user){
        return true;
    }

}
