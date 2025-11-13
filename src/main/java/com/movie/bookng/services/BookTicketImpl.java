package com.movie.bookng.services;

import com.movie.bookng.data.BookTicketDTO;
import com.movie.bookng.validator.BookTicketValidation;

import java.util.Scanner;

public class BookTicketImpl implements  BookedTicket {

    @Override
    public void bookTicket() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Show Id: ");
        Integer showId = sc.nextInt();
        System.out.print("Enter Seat ID: ");
        Integer seatId = sc.nextInt();

        BookTicketDTO bkD = new BookTicketDTO();
        bkD.setShowId(showId);
        bkD.setSeatId(seatId);
        BookTicketValidation.bookTicketValidation(bkD);


    }
}
