package com.movie.bookng.data;

import com.movie.bookng.util.ConfigConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AudiDAO {


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



    //Creating Auditorium.....................................................
    public static void createAdu(AudiDto audiDto) {
        try {
            Connection conn = ds.getConnection();

//            creating auditorium

            String sql = "insert into auditoriums (auditoriumName,roomNo) values (?,?)";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString(1, audiDto.getAuditoriumName());
            smt.setString(2, audiDto.getRoomNumber());
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
}
