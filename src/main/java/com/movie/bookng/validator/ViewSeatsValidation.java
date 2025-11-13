package com.movie.bookng.validator;

import com.movie.bookng.data.ViewSeatDTO;

public class ViewSeatsValidation {

    public static void seatValidation(ViewSeatDTO vs){

    }
    public static void viewSeatsShowIDValidation(Integer showId){
        if(showId == null || showId <= 0) {
            System.err.println("Show ID should not be null or less than zero");
            throw new IllegalArgumentException("Show ID should not be null or less than zero");
        }
    }
}
