package com.movie.bookng.data;

import com.movie.bookng.CustomException.SeatException;
import com.movie.bookng.util.ConfigConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookTicketDAO {

    private static DataSource ds;

    static {
        try {
            ds = ConfigConnection.getDataSource();
        } catch (Exception e) {
            System.err.println("❌ Failed to initialize DataSource: " + e.getMessage());
        }
    }

    public static void bookTicket(BookTicketDTO bkD) {

        String checkSql = "SELECT seatId, status FROM show_seats WHERE showId = ? AND seatId = ? FOR UPDATE";
        String holdSql = "UPDATE show_seats SET status = 'HELD' WHERE showId = ? AND seatId = ?";
        String bookSql = "UPDATE show_seats SET status = 'BOOKED' WHERE showId = ? AND seatId = ?";
        String insertBookingSql = "insert into bookings (userName,userPhone,fkShowId,totalAmount) values (?,?,?,?)";

        try (Connection conn = ds.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            try {
                // Step 1: Check availability
                try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                    ps.setInt(1, bkD.getShowId());
                    ps.setInt(2, bkD.getSeatId());
                    ResultSet rs = ps.executeQuery();

                    if (!rs.next()) {
                        throw new SeatException("❌ Seat does not exist!");
                    }

                    String status = rs.getString("status");
                    if (!"AVAILABLE".equalsIgnoreCase(status)) {
                        throw new SeatException("❌ Seat is not AVAILABLE!");
                    }
                }

                // Step 2: Hold the seat temporarily
                try (PreparedStatement ps = conn.prepareStatement(holdSql)) {
                    ps.setInt(1, bkD.getShowId());
                    ps.setInt(2, bkD.getSeatId());
                    ps.executeUpdate();
                }

                // Step 3: Create booking record
                Integer bookingId ;
                try
                {
                    Scanner sc  = new Scanner(System.in);
                    System.out.println("Enter userName");
                    String userName = sc.nextLine();
                    System.out.println("Enter userPhone");
                    String userPhone = sc.next();
                    System.out.println("Enter ShowId");
                    Integer fkShowId = sc.nextInt();
                    System.out.println("Enter totalAmount");
                    Integer totalAmount = sc.nextInt();
                    PreparedStatement ps = conn.prepareStatement(insertBookingSql);
                    ps.setString(1, userName);
                    ps.setString(2, userPhone);
                    ps.setInt(3, fkShowId);
                    ps.setInt(4, totalAmount);
                    Integer rows = ps.executeUpdate();


                    if (rows == 0 ) {
                        throw new SeatException("❌ Booking failed — no record inserted!");
                    }

                    // Get generated booking ID
                    String bookingIdSql = "select bookingId from bookings where userPhone = ?";

                    PreparedStatement bki = conn.prepareStatement(bookingIdSql);
                    bki.setString(1, userPhone);
                    ResultSet rs = bki.executeQuery();
                    while(rs.next())
                    {
                        bookingId = rs.getInt("bookingId");
                    }

                }

                // Step 4: Confirm booking (mark seat as BOOKED)
                try (PreparedStatement ps = conn.prepareStatement(bookSql)) {
                    ps.setInt(1, bkD.getShowId());
                    ps.setInt(2, bkD.getSeatId());
                    ps.executeUpdate();
                }

                conn.commit(); // ✅ Commit all steps
                System.out.println("✅ Booking successful! Booking ID: " + bookingId);

            } catch (SeatException | SQLException e) {
                conn.rollback(); // Rollback on any failure
                System.err.println("❌ Transaction rolled back: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
