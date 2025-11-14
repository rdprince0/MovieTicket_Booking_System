package com.movie.bookng.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConfigConnection {
    private static final String dbUrl = "jdbc:mysql://127.0.0.1:3306/ticket_booking_system";
    private static final String username = "root";
    private static final String password = "Root@123";
    private static HikariDataSource ds = null;


    static
    {
        try {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(60000);
         ds = new HikariDataSource(config);

        System.out.println("Connected to database using HikariCP connection pool...");

        } catch (Exception e) {

            System.err.println("Failed to initialize database connection: " + e.getMessage());
        }

    }

    public static DataSource getDataSource() throws SQLException
    {
        return ds;
    }
}
