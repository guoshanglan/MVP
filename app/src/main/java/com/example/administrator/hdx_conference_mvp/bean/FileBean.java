package com.example.administrator.hdx_conference_mvp.bean;

/**
 * Created by Administrator on 2019/3/27.
 */

public class FileBean {
    public int ImageId;
    public String name;
    public String size;
    public String date;
    public String power;

    public FileBean(int imageId, String name, String size, String date, String power) {
        ImageId = imageId;
        this.name = name;
        this.size = size;
        this.date = date;
        this.power = power;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
