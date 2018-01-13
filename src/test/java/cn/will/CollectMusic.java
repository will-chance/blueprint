package cn.will;

import cn.will.mapper.AlbumMapper;
import cn.will.mapper.ArtistMapper;
import cn.will.mapper.MusicMapper;
import cn.will.po.Album;
import cn.will.po.AlbumMusic;
import cn.will.po.Artist;
import cn.will.po.Music;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fxml.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created on 2018-01-11 11:11 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class CollectMusic {

    @Autowired
    private ArtistMapper artistMapper;

    @Autowired private AlbumMapper albumMapper;

    @Autowired private MusicMapper musicMapper;

    private String BASE_API = "http://tingapi.ting.baidu" +
            ".com/v1/restserver/ting?format=json&";

    //欧美top
    private String EUROPEAN_TOP_API = "method=baidu.ting.billboard.billList&type=21&size=40&offset=0";

    //JAZZ TOP
    private String JAZZ_TOP_API = "method=baidu.ting.billboard.billList&type=22&size=40&offset=0";

    private String VIDEO_TOP_API = "method=baidu.ting.billboard.billList&type=24&size=40&offset=0";

    private String ARTIST_MUSIC_LIST_API = "method=baidu.ting.artist.getSongList&tinguid=${artistId}&limits=40";

    @Before
    public void before(){

    }

    @Test
    public void crawl(){
            String engTop = loadJson(BASE_API + EUROPEAN_TOP_API);
            JSONArray engArray = JSON.parseObject(engTop).getJSONArray("song_list");
            String jazzTop = loadJson(BASE_API + JAZZ_TOP_API);
            JSONArray jazzArray = JSON.parseObject(jazzTop).getJSONArray("song_list");
            String videoTop = loadJson(BASE_API + JAZZ_TOP_API);
            JSONArray videoArray = JSON.parseObject(videoTop).getJSONArray("song_list");

            List<Artist> artists = new ArrayList<>();
            List<Music> musics = new ArrayList<>();
            List<Album> albums = new ArrayList<>();
            List<AlbumMusic> list = new ArrayList<>();

            List<Integer> albumIds = new ArrayList<>();

            initDBData(engArray, artists, musics, albums, list);
            initDBData(jazzArray, artists, musics, albums, list);
            initDBData(videoArray, artists, musics, albums, list);

            List<Integer> artistIds = new ArrayList<>();
            getArtistTinguid(artistIds,engArray);
            getArtistTinguid(artistIds,jazzArray);
            getArtistTinguid(artistIds,videoArray);

            for (int i = 0; i < artistIds.size(); i++) {
                String api = BASE_API + ARTIST_MUSIC_LIST_API.replace("${artistId}",String.valueOf(artistIds.get(i)));
                String s = loadJson(api);
                JSONArray array = JSON.parseObject(s).getJSONArray("songlist");
                initDBData(array, artists, musics, albums, list);
            }

        System.out.println();

            artistMapper.insertBatch(artists);
            musicMapper.insertBatch(musics);
            albumMapper.insertBatch(albums);
            musicMapper.insertBatchMusicAlbum(list);
    }

    private void initDBData(
            JSONArray arrays,
            List<Artist> artists,
            List<Music> musics,
            List<Album> albums, List<AlbumMusic> list) {
        for (int i = 0; i < arrays.size(); i++) {
            JSONObject object = arrays.getJSONObject(i);
            Integer albumId = object.getInteger("album_id");
            Integer musicId = object.getInteger("song_id");
            if (albumId == 0 || musicId == 0) {
                continue;
            }
            //artist
            Integer artistId = object.getInteger("artist_id");
            String artistName = object.getString("author");
            String pic = object.getString("pic_big");
            //music
            String publishtime = object.getString("publishtime");
            String language = object.getString("language");

            String musicTitle = object.getString("title");
            String lrclink = object.getString("lrclink");
            Integer hot = object.getInteger("hot");
            Integer duration = object.getInteger("file_duration");
            String musicImg = object.getString("pic_radio");
            //album

            String albumTitle = object.getString("album_title");
            String albumCover = object.getString("album_500_500");

            Artist artist = new Artist();
            artist.setId(artistId);
            artist.setArtistName(artistName);
            artist.setDescription(pic);
            if (!artists.contains(artist)){
                artists.add(artist);
            }

            Music music = new Music();
            music.setCover(musicImg);
            music.setDuration(duration);
            music.setHot(hot);
            music.setId(musicId);
            music.setTitle(musicTitle);
            music.setPublishTime(publishtime);
            music.setTitle(musicTitle);
            music.setArtistId(artistId);
            music.setLanguage(language);
            music.setLyric(lrclink);
            if (!musics.contains(music)){
               musics.add(music);
            }

            Album album = new Album();
            album.setId(albumId);
            album.setTitle(albumTitle);
            album.setCover(albumCover);

            if (!albums.contains(album)){
                album.setHot(Math.abs(new Random().nextInt(10000)));
                albums.add(album);
            }

            AlbumMusic albumMusic = new AlbumMusic(musicId,albumId);
            if (!list.contains(albumMusic)){
                list.add(albumMusic);
            }
        }
    }

    private void getArtistTinguid(List<Integer> artists,JSONArray array){
        for (int i =0;i<array.size();i++){
            Integer id = array.getJSONObject(i).getInteger("ting_uid");
            if (!artists.contains(id)){
                artists.add(id);
            }
        }
    }

    public String loadJson(String uri){
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(uri);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line = null;
            while ((line = reader.readLine())!= null) {
                content.append(line);
            }
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
