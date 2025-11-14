package com.movie.bookng.menu;

import com.movie.bookng.data.PaymentDAO;
import com.movie.bookng.data.PaymentDTO;

import java.awt.*;
import java.util.Scanner;

public class PaymentMenu {
    public void paymentCofirm()
    {

        Scanner sc  = new Scanner(System.in);
        System.out.println("Booking Id : ");
        Integer bookingId = sc.nextInt();
        System.out.println("Mobile No : ");
        String  mobileNo = sc.next();
        System.out.println("Enter Payment Amount : ");
        Integer paymentAmount = sc.nextInt();
        PaymentDTO pDTO = new PaymentDTO();
        pDTO.setBookingId(bookingId);
        pDTO.setMobileNo(mobileNo);
        pDTO.setPaymentAmount(paymentAmount);


        System.out.println("Choose payment method..");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. UPI");
        System.out.println("4. Exit");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> PaymentDAO.paymentDAO(pDTO,"Cash");
            case 2 -> PaymentDAO.paymentDAO(pDTO,"Card");
            case 3 -> PaymentDAO.paymentDAO(pDTO,"UPI");
            case 4 -> System.exit(0);
            default -> System.out.println("Invalid choice");
        }
    }
}
