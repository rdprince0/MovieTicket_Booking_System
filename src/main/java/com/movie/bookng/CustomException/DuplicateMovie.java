package com.movie.bookng.CustomException;

public class DuplicateMovie extends RuntimeException {
    public DuplicateMovie(String message) {
        super(message);

    }
}
