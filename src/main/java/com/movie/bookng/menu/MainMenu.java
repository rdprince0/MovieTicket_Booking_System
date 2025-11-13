package com.movie.bookng.menu;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(".....Select user type....");
        System.out.println("1. Admin");
        System.out.println("2. User");
        System.out.println("3. Exit");
        System.out.print("Select an option : ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> AdminMenu.adminMenu();
            case 2 -> UserMenu.userMenu();
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid option");
        }
    }
}
