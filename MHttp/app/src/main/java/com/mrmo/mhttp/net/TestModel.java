package com.mrmo.mhttp.net;

/**
 * Created by moguangjian on 2017/3/12.
 */

public class TestModel {


    /**
     * status : success
     * image : /UploadFile/Image/20161213/20161213195217_HULK6N.jpg
     * linkUrl :
     * duration : 4
     * welcomeId : 14
     */

    private String status;
    private String image;
    private String linkUrl;
    private int duration;
    private int welcomeId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getWelcomeId() {
        return welcomeId;
    }

    public void setWelcomeId(int welcomeId) {
        this.welcomeId = welcomeId;
    }
}
