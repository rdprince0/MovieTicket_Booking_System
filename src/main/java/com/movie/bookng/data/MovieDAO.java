package com.movie.bookng.data;

import com.movie.bookng.CustomException.DuplicateMovie;
import com.movie.bookng.util.ConfigConnection;
import com.movie.bookng.validator.AudiIdException;
import com.movie.bookng.validator.MovieIdException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Scanner;

public class MovieDAO {
    private static DataSource ds;

    static {
        DataSource temp = null;
        try {
            temp = ConfigConnection.getDataSource();
        } catch (Exception e) {
            System.err.println("❌ Failed to initialize DataSource: " + e.getMessage());
        }
        ds = temp;
    }

//    Adding Movie.....................................................

    public static void addMovie(MovieDTo movieDTo)  {


        try{

            Connection conn = ds.getConnection();

            try
            {
                // Duplicate Movie Check....................................
                String checkSql = "select * from movies where movieTittle = ?";
                PreparedStatement ps1 = conn.prepareStatement(checkSql);
                ps1.setString(1, movieDTo.getMovieTittle());
                ResultSet rs1 = ps1.executeQuery();
                if(rs1.next()) {
                    throw new DuplicateMovie("Movie already exists : " + movieDTo.getMovieTittle());
                }
            }
            catch(DuplicateMovie dm){
                System.err.println("Movie already exists : " + movieDTo.getMovieTittle());
                throw dm;
            }

//  Insert new Movie........................................

            String sql = "insert into ticket_booking_system.movies (movieTittle,language,duration_min,certification) values (?,?,?,?) ";


            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString(1, movieDTo.getMovieTittle());
            smt.setString(2,movieDTo.getMovieLanguage());
            smt.setInt(3,movieDTo.getDuration_min());
            smt.setString(4, movieDTo.getCertification());
            Integer rowEffected =smt.executeUpdate();

            if(rowEffected > 0) {
                System.out.println("Movie Added Successfully!");
            }
            else
            {
                System.out.println("⚠️ Movie not added. Please check your data.");
            }

        }
        catch (SQLException e)
        {
            System.err.println("❌ Database error (HikariCP)" );
            throw new RuntimeException("❌ Database error (HikariCP)", e);
//            e.printStackTrace();
        }
    }

//Creating Auditorium.....................................................
    public static void createAdu(MovieDTo movieDtO) {
        try {
            Connection conn = ds.getConnection();

//            creating auditorium

            String sql = "insert into auditoriums (auditoriumName,roomNo) values (?,?)";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString(1, movieDtO.getAuditoriumName());
            smt.setString(2, movieDtO.getRoomNumber());
            Integer rowEffected = smt.executeUpdate();
            if (rowEffected > 0) {
                System.out.println("Auditorium Added Successfully!");
            } else {
                System.out.println("⚠️Auditorium not added. Please check your data.");

            }
        } catch (SQLException e) {
            System.err.println("❌ Database error (HikariCP)");
            throw new RuntimeException("❌ Database error (HikariCP)", e);
        }
    }

//    Creating Show.........................................................

    public  static void createShow(MovieDTo movieDTo) {


        try
        {
            Connection conn = ds.getConnection();


//            Checking MovieId and AudiID present Or not
            try
            {
                String sql = "select * from movies where movieId = ?";
                PreparedStatement smt = conn.prepareStatement(sql);
                smt.setInt(1, movieDTo.getMovieId());
                ResultSet rs = smt.executeQuery();
                if(!rs.next()) {
                    throw new MovieIdException("Movie already exists : " + movieDTo.getMovieId());
                }

                String sql2 ="select * from auditoriums where auditoriumId = ?";
                PreparedStatement smt2 = conn.prepareStatement(sql2);
                smt2.setInt(1, movieDTo.getAudiId());
                ResultSet rs2 = smt2.executeQuery();
                if(!rs2.next()) {
                    throw new AudiIdException("Auditorium Id not exists : " + movieDTo.getAudiId());
                }
            }
            catch(MovieIdException mi)
            {
                System.err.println("Movie ID not exists : " + movieDTo.getMovieId());
                throw mi;
            }
            catch(AudiIdException ai)
            {
                System.err.println("AudiId not exists : " + movieDTo.getAudiId());
                throw ai;
            }




//            Inserting show...............

            String sql = "Insert into shows (movieID,auditoriumId,showTime,showEnd) values (?,?,?,?)";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setInt(1, movieDTo.getMovieId());
            smt.setInt(2, movieDTo.getAudiId());
            smt.setTime(3,movieDTo.getShowStartTime());
            smt.setTime(4,movieDTo.getShowEndTime());
            Integer rowEffected = smt.executeUpdate();
            if(rowEffected > 0) {
                System.out.println("Show Added Successfully!");
            }
            else {
                System.out.println("Show not added. Please check your data.");
            }
        }
        catch(SQLException e)
        {
            System.err.println("❌ Database error (HikariCP)");
            throw new RuntimeException("❌ Database error (HikariCP)", e);
        }

    }
}
