package com.movie.bookng.menu;

import com.movie.bookng.services.MovieServicesImp;

import java.util.Scanner;

public class AdminMenu {
    private static final  MovieServicesImp msl = new MovieServicesImp();
    public static void adminMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println(".....Select an option....");
        System.out.println("1. Add movie");
        System.out.println("2. Create Auditorium");
        System.out.println("3. Create Show");
        System.out.println("4. Exit");
        System.out.print("Select an option : ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> msl.addMovie();
            case 2 -> msl.createAuditorium();
            case 3 -> msl.createShows();
            case 4 -> System.exit(0);
            default -> System.out.println("Invalid option");
        }
    }
}
