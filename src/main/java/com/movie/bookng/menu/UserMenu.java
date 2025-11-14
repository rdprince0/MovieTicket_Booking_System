package com.movie.bookng.menu;

import com.movie.bookng.services.BookTicketImpl;
import com.movie.bookng.services.ViewSeatsIml;

import java.util.Scanner;

public class UserMenu {
    public static void userMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("....Select an option.... ");
        System.out.println("1. View Seats");
        System.out.println("2. Book Tickets");
        System.out.println("3. Confirm Payment");
        System.out.println("4. Cancel Payment");
        System.out.println("5. Exit");
        System.out.print("Enter your choice : ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:{
                ViewSeatsIml  viewSeatsIml = new ViewSeatsIml();
                viewSeatsIml.viewSeats();
                break;
            }
            case 2 :
            {
                BookTicketImpl  bookTicketImpl = new BookTicketImpl();
                bookTicketImpl.bookTicket();
                break;
            }
            case 3 :
            {
                System.out.println("Confirm Payment");
                break;
            }
            case 4 :
            {
                System.out.println("Cancel Payment");
                break;
            }
            case 5 :
            {
                System.exit(0);
                break;
            }
            default :
            {
                System.out.println("Invalid option");
            }
        }
    }
}
