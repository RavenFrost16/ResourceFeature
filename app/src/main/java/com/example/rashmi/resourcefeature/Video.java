package com.example.rashmi.resourcefeature;

/**
 * Created by RASHMI on 04-03-2018.
 */

public class Video {
    private String videoName, videoDesc, videoUrl, videoThumbnail;

    public Video(){}


    public Video(String videoName, String videoDesc, String videoUrl, String videoThumbnail){
        this.videoName = videoName;
        this.videoDesc = videoDesc;
        this.videoUrl = videoUrl;
        this.videoThumbnail = videoThumbnail;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }
}
