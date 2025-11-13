package com.movie.bookng.data;

import java.sql.Time;

public class MovieDTo {
    private String movieTittle;
    private String movieLanguage;
    private Integer duration_min;
    private String certification;
    private String auditoriumName;
    private String roomNumber;
    private Integer movieId;
    private Integer audiId;
    private Time showStartTime;
    private Time showEndTime;

    public Integer getAudiId() {
        return audiId;
    }

    public void setAudiId(Integer audiId) {
        this.audiId = audiId;
    }


    public Time getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(Time showStartTime) {
        this.showStartTime = showStartTime;
    }

    public Time getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(Time showEndTime) {
        this.showEndTime = showEndTime;
    }

    public String getMovieTittle() {
        return movieTittle;
    }

    public void setMovieTittle(String movieTittle) {
        this.movieTittle = movieTittle;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public Integer getDuration_min() {
        return duration_min;
    }

    public void setDuration_min(Integer duration_min) {
        this.duration_min = duration_min;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getAuditoriumName() { return auditoriumName; }

    public void setAuditoriumName(String auditoriumName) { this.auditoriumName = auditoriumName; }

    public void setMovieId(Integer movieId) { this.movieId = movieId; }
    public Integer getMovieId() { return movieId; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
}
