package com.movie.bookng.data;

import java.sql.Time;

public class MovieDTo {
    private String movieTittle;
    private String movieLanguage;
    private Integer duration_min;
    private String certification;





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






}
