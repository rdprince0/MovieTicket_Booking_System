package com.movie.bookng.validator;

import com.movie.bookng.data.MovieDTo;

import java.sql.Time;

public class ShowsValidator {
    Integer movieId;
    Integer showId;
    Time showStartTime;
    Time showEndTime;

    public static void showsValidator(MovieDTo movieDTo) {
        movieIDValidator(movieDTo.getMovieId());
        audiIdValidator(movieDTo.getAudiId());
        validateShowTimes(movieDTo);
    }
    public static void movieIDValidator(Integer movieId) {
        if(movieId == null || movieId <= 0) {
            System.err.println("Movie ID should not be null or less than zero");
            throw new IllegalArgumentException("Movie ID should not be null or less than zero");
        }
    }
    public static void audiIdValidator(Integer showId) {
        if(showId == null || showId <= 0)
        {
            System.err.println("Show ID should not be null or less than zero");
            throw new IllegalArgumentException("Show ID should not be null or less than zero");
        }
    }
    private static void validateShowTimes(MovieDTo dto) {
        if (dto.getShowStartTime() == null || dto.getShowEndTime() == null) {
            throw new IllegalArgumentException("❌ Show start time and end time must not be null!");
        }

        if (dto.getShowEndTime().before(dto.getShowStartTime())) {
            throw new IllegalArgumentException("❌ Show end time must be after start time!");
        }
    }
}

