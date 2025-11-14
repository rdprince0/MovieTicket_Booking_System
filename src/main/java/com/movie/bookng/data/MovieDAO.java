package com.movie.bookng.data;

import com.movie.bookng.CustomException.DuplicateMovie;
import com.movie.bookng.util.ConfigConnection;

import javax.sql.DataSource;
import java.sql.*;

public class MovieDAO {
    private static DataSource ds;

    static {
        DataSource temp = null;
        try {
            temp = ConfigConnection.getDataSource();
        } catch (Exception e) {
            System.err.println(" Failed to initialize DataSource: " + e.getMessage());
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
                System.out.println("Movie not added. Please check your data.");
            }

        }
        catch (SQLException e)
        {
            System.err.println("Database error (HikariCP)" );
            throw new RuntimeException(" Database error (HikariCP)", e);
//            e.printStackTrace();
        }
    }

}
