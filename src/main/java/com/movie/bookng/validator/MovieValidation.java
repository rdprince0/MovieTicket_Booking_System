package com.movie.bookng.validator;

import com.movie.bookng.data.MovieDAO;
import com.movie.bookng.data.MovieDTo;

import javax.swing.*;
import java.util.Set;

public class MovieValidation {

    public static void detailsValidator(MovieDTo mdto)
    {
        movieNameValidation(mdto.getMovieTittle());
        movieLanguageValidation(mdto.getMovieLanguage());
        movieDurationValidation(mdto.getDuration_min());
        movieCertificationValidation(mdto.getCertification());

    }

    public static void movieNameValidation(String movieName)
    {
        if (movieName == null || movieName.trim().isEmpty()) {
            System.err.println("Movie name should not be empty");
            throw new IllegalArgumentException("Movie name is null or empty");
        } else if (movieName.length() < 3 || movieName.length() > 45) {
            System.err.println("Invalid  Movie name length");
            throw new IllegalArgumentException("Movie name must be between 3 and 45 characters");
        }
    }
    public static void movieLanguageValidation(String language)
    {
        if (language == null || language.trim().isEmpty()) {
            System.err.println("language should not be empty");
            throw new IllegalArgumentException("Language is null or empty");
        } else if (language.length() < 3 || language.length() > 45) {
            System.err.println("Invalid language length");
            throw new IllegalArgumentException("Language must be between 3 and 45 characters");
        }
    }
    public static void movieDurationValidation(Integer duration)
    {
        if (duration == null || duration < 1 || duration < 60) {
            throw new IllegalArgumentException("Movie duration should be between greater than 60 minutes");
        }
    }

    public static void movieCertificationValidation(String cer) {
        if (cer == null || cer.trim().isEmpty()) {
            throw new IllegalArgumentException("Certification should not be empty");
        }

        Set<String> validCertifications = Set.of(
                "U", "A", "S", "UA", "U/A", "G", "PG-13", "PG-17", "NC-17", "PG"
        );

        if (!validCertifications.contains(cer.toUpperCase())) {
            throw new IllegalArgumentException("Movie certification is not valid");
        }
    }
}
