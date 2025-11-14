package com.movie.bookng.data;

import com.movie.bookng.CustomException.SeatException;
import com.movie.bookng.util.ConfigConnection;

import javax.sql.DataSource;
import java.sql.*;

public class BookTicketDAO {

    private static DataSource ds;

    static {
        try {
            ds = ConfigConnection.getDataSource();
        } catch (Exception e) {
            System.err.println(" Failed to initialize DataSource: " + e.getMessage());
        }
    }

    public static void bookTicket(BookTicketDTO bkD) {
        String checkSql = "SELECT seatId, status FROM show_seats WHERE showId = ? AND seatId = ? FOR UPDATE";
        String holdSql = "UPDATE show_seats SET status = 'HELD' WHERE showId = ? AND seatId = ?";
        String bookSql = "UPDATE show_seats SET status = 'BOOKED' WHERE showId = ? AND seatId = ?";
        String insertBookingSql = "INSERT INTO bookings (userName,userPhone,fkShowId,totalAmount) VALUES (?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Step 1: Check seat availability
            ps = conn.prepareStatement(checkSql);
            ps.setInt(1, bkD.getShowId());
            ps.setInt(2, bkD.getSeatId());
            rs = ps.executeQuery();

            if (!rs.next()) {
                throw new SeatException("Seat does not exist!");
            }

            String status = rs.getString("status");
            if (!"AVAILABLE".equalsIgnoreCase(status)) {
                throw new SeatException("Seat is not AVAILABLE!");
            }
            rs.close();
            ps.close();

            // Step 2: Hold the seat temporarily
            ps = conn.prepareStatement(holdSql);
            ps.setInt(1, bkD.getShowId());
            ps.setInt(2, bkD.getSeatId());
            ps.executeUpdate();
            ps.close();

            // Step 3: Insert booking record
            int bookingId = 0;
            ps = conn.prepareStatement(insertBookingSql);
            ps.setString(1, bkD.getUserName());
            ps.setString(2, bkD.getUserMobileNo());
            ps.setInt(3, bkD.getShowId());
            ps.setInt(4, bkD.getTotalAmount());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new SeatException("Booking failed — no record inserted!");
            }

//            Featching the booking Id...................................................................................
            String sqlBook = "select bookingId from bookings where userPhone=?";
            PreparedStatement bookingStmt = conn.prepareStatement(sqlBook);
            bookingStmt.setString(1, bkD.getUserMobileNo());
            rs = bookingStmt.executeQuery();
            if (rs.next()) {
                bookingId = rs.getInt("bookingId");
            } else {
                throw new SeatException("Failed to retrieve booking ID!");
            }
            rs.close();
            ps.close();

            // Step 4: Confirm booking (mark seat as BOOKED)
            ps = conn.prepareStatement(bookSql);
            ps.setInt(1, bkD.getShowId());
            ps.setInt(2, bkD.getSeatId());
            ps.executeUpdate();
            ps.close();

            conn.commit(); //  Commit transaction
            System.out.println("Booking successful! Booking ID: " + bookingId);

        } catch (SeatException | SQLException e) {
            try {
                if (conn != null) conn.rollback();
                System.err.println(" Transaction rolled back: " + e.getMessage());
            } catch (SQLException ex) {
                System.err.println("Error rolling back transaction: " + ex.getMessage());
            }
        } finally {
            try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException ex) {}
            try { if (conn != null) conn.close(); } catch (SQLException ex) {}
        }
    }
}
