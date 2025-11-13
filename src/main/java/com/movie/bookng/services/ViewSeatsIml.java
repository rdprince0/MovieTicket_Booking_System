package com.movie.bookng.services;

import com.movie.bookng.data.ViewSeatDAO;
import com.movie.bookng.data.ViewSeatDTO;
import com.movie.bookng.validator.ViewSeatsValidation;

import java.util.Scanner;

public class ViewSeatsIml implements ViewSeats {

    @Override
    public  void viewSeats() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Show ID : ");
        Integer showId = sc.nextInt();
        ViewSeatDTO vs = new ViewSeatDTO();
        vs.setShowId(showId);
        ViewSeatsValidation.viewSeatsShowIDValidation(showId);
        ViewSeatDAO.viewSeats(showId);

    }
}
