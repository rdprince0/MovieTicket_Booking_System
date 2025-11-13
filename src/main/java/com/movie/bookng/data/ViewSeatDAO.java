package com.movie.bookng.data;

import com.movie.bookng.util.ConfigConnection;
import com.movie.bookng.CustomException.ViewSeatException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewSeatDAO {

    private static DataSource ds;

    static {
        try {
            ds = ConfigConnection.getDataSource();
        } catch (Exception e) {
            System.err.println("❌ Failed to initialize DataSource: " + e.getMessage());
        }
    }

    public static void viewSeats(int showId) {
        String sql = """
            SELECT s.rowLabel, s.seatNo, ss.status
            FROM show_seats ss
            JOIN seats s ON ss.showSeatId = s.seatId
            WHERE ss.showId = ?
            ORDER BY s.rowLabel, s.seatNo
            """;

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, showId);
            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) { // No seats found
                throw new ViewSeatException("Show ID does not exist: " + showId);
            }

            System.out.println("\n🎟️ Seat Map for Show ID: " + showId);
            System.out.println("Legend: [A]=Available  [B]=Booked  [H]=Held\n");

            String currentRow = ""; // Track current row

            while (rs.next()) {
                String row = rs.getString("rowLabel");
                int seatNo = rs.getInt("seatNo");
                String status = rs.getString("status");

                // Print row label only when it changes
                if (!row.equals(currentRow)) {
                    currentRow = row;
                    System.out.print("\n" + currentRow + ": ");
                }

                // Simple seat display
                String seatDisplay = "[A]";
                if ("BOOKED".equalsIgnoreCase(status))
                {
                    seatDisplay = "[B]";
                }
                else if ("HELD".equalsIgnoreCase(status))
                {
                    seatDisplay = "[H]";
                }

                System.out.printf("%s%-2d ", seatDisplay, seatNo);
            }

            System.out.println(); // final newline

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (ViewSeatException vse) {
            System.err.println(vse.getMessage());
        }
    }
}
