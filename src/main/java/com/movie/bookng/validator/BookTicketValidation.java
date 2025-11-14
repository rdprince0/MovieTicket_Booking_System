package com.movie.bookng.validator;

import com.movie.bookng.data.BookTicketDTO;

public class BookTicketValidation {

    public static void  bookTicketValidation(BookTicketDTO bkD) {

        showIdValidation(bkD.getShowId());
        validateSeatId(bkD.getSeatId());
        validateUserName(bkD.getUserName());
        validateMobileNumber(bkD.getUserMobileNo());
        validateTotalPrice(bkD.getTotalAmount());


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

    public static void validateUserName(String userName)  {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (!userName.matches("[a-zA-Z ]{3,}")) {
            throw new IllegalArgumentException("Username must be at least 3 characters and contain only letters and spaces.");
        }
    }


    public static void validateMobileNumber(String mobileNo) {
        if (mobileNo == null || mobileNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Mobile number cannot be empty.");
        }
        if (!mobileNo.matches("\\d{10}")) {
            throw new IllegalArgumentException("Mobile number must be exactly 10 digits.");
        }
    }

    public static void validateTotalPrice(Integer totalPrice) {
        if (totalPrice == null) {
            throw new IllegalArgumentException("Total ticket price cannot be null.");
        }
        if (totalPrice <= 0) {
            throw new IllegalArgumentException("Total ticket price must be greater than 0.");
        }
    }
}
