package com.movie.bookng.validator;

import com.movie.bookng.data.BookTicketDTO;

public class BookTicketValidation {

    public static void  bookTicketValidation(BookTicketDTO bkD) {

        showIdValidation(bkD.getShowId());


    }

    public static void showIdValidation(Integer showId) {
        if(showId == null || showId <= 0)
        {
            System.err.println("Show ID should not be null or less than zero");
            throw new IllegalArgumentException("Show ID should not be null or less than zero");
        }
    }
    public static void validateSeatId(Integer seatId) {

        if(seatId == null || seatId <= 0)
        {
            System.err.println("Seat ID should not be null or less than zero");
            throw new IllegalArgumentException("Seat ID should not be null or less than zero");
        }
    }
}
