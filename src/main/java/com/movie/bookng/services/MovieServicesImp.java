package com.movie.bookng.services;

import com.movie.bookng.data.MovieDAO;
import com.movie.bookng.data.MovieDTo;
import com.movie.bookng.validator.AudiValidator;
import com.movie.bookng.validator.MovieValidation;
import com.movie.bookng.validator.ShowsValidator;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MovieServicesImp implements MovieService {
    private static final Scanner sc = new Scanner(System.in);
    private static final MovieDTo movieDto = new MovieDTo();
    @Override
    public void addMovie() {
        System.out.println("Enter Movie Name: ");
        String movieName = sc.nextLine();
        System.out.println("Enter Movie Language: ");
        String movieLanguage = sc.nextLine();
        System.out.println("Enter Movie Duration in minutes: ");
        Integer movieDuration = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Movie certification: ");
        String movieCertification = sc.nextLine();

        movieDto.setMovieTittle(movieName);
        movieDto.setMovieLanguage(movieLanguage);
        movieDto.setDuration_min(movieDuration);
        movieDto.setCertification(movieCertification);
        MovieValidation.detailsValidator(movieDto);
        MovieDAO.addMovie(movieDto);

    }

    @Override
    public void createAuditorium() {
        System.out.println("Enter Auditorium Name: ");
        String auditoriumName = sc.nextLine();
        System.out.println("Enter room number: ");
        String auditoriumRoomNumber = sc.next();
        movieDto.setAuditoriumName(auditoriumName);
        movieDto.setRoomNumber(auditoriumRoomNumber);
        AudiValidator.audiValidator(movieDto);
        MovieDAO.createAdu(movieDto);
    }

    @Override
    public void createShows() {
        System.out.println("Enter movie Id");
        Integer movieId = sc.nextInt();
        System.out.println("Enter auditorium Id");
        Integer audId = sc.nextInt();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter time Show Time (HH:MM): ");
        String t1 = sc.nextLine();
        // Convert to LocalTime
        LocalTime  showTime= LocalTime.parse(t1, DateTimeFormatter.ofPattern("HH:mm"));
        // Convert LocalTime to java.sql.Time for database
        Time showStartTime = Time.valueOf(showTime);

        System.out.print("Enter time Show Time (HH:MM): ");
        String t2 = sc.nextLine();
        // Convert to LocalTime
        LocalTime  showEnd= LocalTime.parse(t2, DateTimeFormatter.ofPattern("HH:mm"));
        // Convert LocalTime to java.sql.Time for database
        Time showEndTime = Time.valueOf(showEnd);
        movieDto.setMovieId(movieId);
        movieDto.setAudiId(audId);
        movieDto.setShowStartTime(showStartTime);
        movieDto.setShowEndTime(showEndTime);
        ShowsValidator.showsValidator(movieDto);
        MovieDAO.createShow(movieDto);
    }

}
