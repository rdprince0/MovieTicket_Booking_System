package com.movie.bookng.services;

import com.movie.bookng.data.BookTicketDAO;
import com.movie.bookng.data.BookTicketDTO;
import com.movie.bookng.validator.BookTicketValidation;

import java.util.Scanner;

public class BookTicketImpl implements  BookedTicket {

    @Override
    public void bookTicket() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Show Id: ");
        Integer showId = sc.nextInt();
        System.out.println("Enter Seat ID: ");
        Integer seatId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter userName: ");
        String userName = sc.nextLine();
        System.out.println("Enter User Mobile No: ");
        String userMobileNo = sc.nextLine();
        System.out.println("Enter total ticket price: ");
        Integer totalTicketPrice = sc.nextInt();

        BookTicketDTO bkD = new BookTicketDTO();
        bkD.setShowId(showId);
        bkD.setSeatId(seatId);
        bkD.setUserName(userName);
        bkD.setUserMobileNo(userMobileNo);
        bkD.setTotalAmount(totalTicketPrice);
        BookTicketValidation.bookTicketValidation(bkD);

        BookTicketDAO.bookTicket(bkD);


    }
}
