package com.movie.bookng.validator;

import com.movie.bookng.data.AudiDto;
import com.movie.bookng.data.MovieDTo;

import javax.xml.validation.Validator;

public class AudiValidator {
    public static void audiValidator(AudiDto audiDto)
    {
        validateAudi(audiDto.getAuditoriumName());
        roomValidator(audiDto.getAuditoriumName());
    }

    public static void validateAudi(String audiName)
    {
        if (audiName == null || audiName.trim().isEmpty()) {
            System.err.println("Auditorium name should not be empty");
            throw new IllegalArgumentException("Auditorium name is null or empty");
        } else if (audiName.length() < 3 || audiName.length() > 45) {
            System.err.println("Invalid  Auditorium name length");
            throw new IllegalArgumentException("Auditorium name must be between 3 and 45 characters");
        }
    }
    public static void roomValidator(String roomName)
    {
        if (roomName == null || roomName.trim().isEmpty()) {
            System.err.println("Room name should not be empty");
            throw new IllegalArgumentException("Room name is null or empty");
        } else if (roomName.length() >= 2 && roomName.length() <= 5) {
            System.err.println("Invalid  Room name length");
            throw new IllegalArgumentException("Room name must be between 3 and 5 characters");
        }
    }
}
