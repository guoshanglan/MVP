package com.example.administrator.hdx_conference_mvp.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/10/18.
 * 存储在本地数据库
 */
@Entity
public class TagString {
    @Id(autoincrement = true)
    private Long _id;
    @Property(nameInDb = "title")
    private String title;
    public String getTitle() {
        return this.title;
    }

    public TagString(String title) {
        this.title = title;
    }

    @Generated(hash = 5470441)
    public TagString(Long _id, String title) {
        this._id = _id;
        this.title = title;
    }

    @Generated(hash = 1671322412)
    public TagString() {
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public long getId() {
        return this._id;
    }
    public void setId(long id) {
        this._id = id;
    }
    

    @Override
    public String toString() {
        return "TagString{" +
                "id=" + _id +
                ", title='" + title + '\'' +
                '}';
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }
}
