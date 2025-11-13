package com.movie.bookng.menu;

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
            case 1 -> System.out.println("View Seats");
            case 2 -> System.out.println("Book Tickets");
            case 3 -> System.out.println("Confirm Payment");
            case 4 -> System.out.println("Cancel Payment");
            case 5 -> System.exit(0);
            default -> System.out.println("Invalid option");
        }
    }
}
