package com.movie.bookng.services;

import com.movie.bookng.data.*;
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

    @Override
    public void addMovie() {
        MovieDTo movieDto = new MovieDTo();
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
        AudiDto  audiDto = new AudiDto();
        System.out.println("Enter Auditorium Name: ");
        String auditoriumName = sc.nextLine();
        System.out.println("Enter room number: ");
        String auditoriumRoomNumber = sc.next();
        audiDto.setAuditoriumName(auditoriumName);
        audiDto.setRoomNumber(auditoriumRoomNumber);
        AudiValidator.audiValidator(audiDto);
        AudiDAO.createAdu(audiDto);
    }

    @Override
    public void createShows() {
        ShowDTo showDto = new ShowDTo();
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
        showDto.setMovieId(movieId);
        showDto.setAudiId(audId);
        showDto.setShowStartTime(showStartTime);
        showDto.setShowEndTime(showEndTime);
        ShowsValidator.showsValidator(showDto);
        ShowDAO.createShow(showDto);
    }

}
