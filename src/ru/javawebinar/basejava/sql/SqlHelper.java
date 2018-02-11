package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.*;

public class SqlHelper {

    public static ConnectionFactory getConnection(String dbUrl, String dbUser, String dbPassword) {
        return () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public static void doQuery(ConnectionFactory connFac, String sql, String... setParams) {
        try (Connection conn = connFac.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < setParams.length; i++) {
                ps.setString(i + 1, setParams[i]);
            }
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public static void doQuery(ConnectionFactory connFac, String sql) {
        try (Connection conn = connFac.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public static ResultSet getResultSet(ConnectionFactory connFac, String sql, String... setParams) {
        try (Connection conn = connFac.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < setParams.length; i++) {
                ps.setString(i + 1, setParams[i]);
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public static ResultSet getResultSet(ConnectionFactory connFac, String sql) {
        try (Connection conn = connFac.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
