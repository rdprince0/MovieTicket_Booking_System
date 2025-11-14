package com.movie.bookng.data;

import com.movie.bookng.CustomException.BookingException;
import com.movie.bookng.util.ConfigConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO {

    private static DataSource ds;

    static {
        try {
            ds = ConfigConnection.getDataSource();
        } catch (Exception e) {
            System.err.println("Failed to initialize DataSource : " + e.getMessage());
        }
    }

    public static void paymentDAO(PaymentDTO pDTO, String method) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);  // Transaction start

            // 1. Checking Booking ID
            try {
                String bsql = "SELECT bookingId FROM bookings WHERE userPhone = ? AND bookingId = ?";
                pstmt = conn.prepareStatement(bsql);
                pstmt.setString(1, pDTO.getMobileNo());
                pstmt.setInt(2, pDTO.getBookingId());
                ResultSet rs = pstmt.executeQuery();

                if (!rs.next()) {
                    throw new BookingException("Booking Id not exist : " + pDTO.getBookingId());
                }
            }
            catch (BookingException ex) {
                System.err.println(ex.getMessage());
                return;   // Stop if booking invalid
            }

            // 2. Insert Payment Record
            String txnRef = "TXN" + System.currentTimeMillis();

            String ps = "INSERT INTO payments(fkBookingId, amount, method, status, tnxRef, paidAt) "
                    + "VALUES(?, ?, ?, ?, ?, NOW())";

            pstmt = conn.prepareStatement(ps);
            pstmt.setInt(1, pDTO.getBookingId());
            pstmt.setDouble(2, pDTO.getPaymentAmount());
            pstmt.setString(3, method);
            pstmt.setString(4, "SUCCESS");
            pstmt.setString(5, txnRef);

            int row = pstmt.executeUpdate();
            if (row == 0) {
                throw new SQLException("Payment not saved!");
            }

            // 3. Update Booking Status
            String usql = "UPDATE bookings SET status = 'CONFIRMED' WHERE bookingId = ?";
            pstmt = conn.prepareStatement(usql);
            pstmt.setInt(1, pDTO.getBookingId());

            int updated = pstmt.executeUpdate();
            if (updated == 0) {
                throw new SQLException("Booking Status not updated!");
            }

            conn.commit();
            System.out.println("Payment Successful. Transaction Ref : " + txnRef);
            System.out.println("Booking Confirmed Successfully.");

        }
        catch (SQLException e) {
            System.err.println("SQL Exception : " + e.getMessage());

            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ignored) {}

        }
        finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException ignored) {}
            try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
        }
    }
}
