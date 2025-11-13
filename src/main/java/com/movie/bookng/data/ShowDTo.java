package com.movie.bookng.data;

import java.sql.Time;

public class ShowDTo {

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

    public void setMovieId(Integer movieId) { this.movieId = movieId; }
    public Integer getMovieId() { return movieId; }
}
