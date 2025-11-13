package com.movie.bookng.data;

import com.movie.bookng.util.ConfigConnection;
import com.movie.bookng.validator.AudiIdException;
import com.movie.bookng.validator.MovieIdException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowDAO {



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



//      Creating Show.........................................................

    public  static void createShow(ShowDTo showDTo) {


        try
        {
            Connection conn = ds.getConnection();


//            Checking MovieId and AudiID present Or not
            try
            {
                String sql = "select * from movies where movieId = ?";
                PreparedStatement smt = conn.prepareStatement(sql);
                smt.setInt(1, showDTo.getMovieId());
                ResultSet rs = smt.executeQuery();
                if(!rs.next()) {
                    throw new MovieIdException("Movie ID not exists : " + showDTo.getMovieId());
                }

                String sql2 ="select * from auditoriums where auditoriumId = ?";
                PreparedStatement smt2 = conn.prepareStatement(sql2);
                smt2.setInt(1, showDTo.getAudiId());
                ResultSet rs2 = smt2.executeQuery();
                if(!rs2.next()) {
                    throw new AudiIdException("Auditorium Id not exists : " + showDTo.getAudiId());
                }
            }
            catch(MovieIdException mi)
            {
                System.err.println("Movie ID not exists : " + showDTo.getMovieId());
                throw mi;
            }
            catch(AudiIdException ai)
            {
                System.err.println("AudiId not exists : " + showDTo.getAudiId());
                throw ai;
            }




//            Inserting show...............

            String sql = "Insert into shows (movieID,auditoriumId,showTime,showEnd) values (?,?,?,?)";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setInt(1, showDTo.getMovieId());
            smt.setInt(2, showDTo.getAudiId());
            smt.setTime(3,showDTo.getShowStartTime());
            smt.setTime(4,showDTo.getShowEndTime());
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
