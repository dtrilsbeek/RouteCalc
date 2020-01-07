package database;

import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
public class Database {

    protected static String host = "localhost";
    protected static String dbName = "routeUsers";
    protected static String user = "routeCalc";
    protected static String pass = "routeCalc";
    protected static int port = 3306;
    private static String userId = "id";
    private static String userName = "name";
    private static String userPass = "password";
    private static final Logger LOGGER = Logger.getLogger( Database.class.getName() );

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://" + host + ":"+port+"/"+dbName,
                user, pass);   // For MySQL only
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        ResultSet rset = null;

        try (
                Connection conn = this.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            String strSelect = "select * from user";
            rset = stmt.executeQuery(strSelect);

            LOGGER.log( Level.FINEST, "The records selected are:");
            int rowCount = 0;
            while (rset.next()) {

                User p = new User(
                        rset.getInt(userId),
                        rset.getString(userName),
                        rset.getString(userPass)
                );

                users.add(p);

                LOGGER.log( Level.FINE, "userDTO GetAll: {0}" , p);
                ++rowCount;
            }
            LOGGER.log( Level.FINE, "Total number of records = {0}", rowCount);

        } catch (SQLException ex) {
            LOGGER.log( Level.SEVERE, ex.toString(), ex );
        } finally {
            if ( rset != null) {
                try {
                    rset.close();
                } catch (SQLException ex) {
                    LOGGER.log( Level.SEVERE, ex.toString(), ex );
                }
            }
        }

        return users;
    }

    public User getUserById(int id) {

        User user = null;
        ResultSet rset = null;

        try (
                Connection conn = this.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            String strSelect = String.format("select * from user WHERE id = %d;", id);
            rset = stmt.executeQuery(strSelect);

            LOGGER.log( Level.FINEST, "The records selected are:");

            while (rset.next()) {

                User p = new User(
                        rset.getInt(userId),
                        rset.getString(userName),
                        rset.getString(userPass)
                );

                LOGGER.log( Level.FINE, "userDTO GetById: {0}" , p);

                user = p;
            }

        } catch (SQLException ex) {
            LOGGER.log( Level.SEVERE, ex.toString(), ex );
        } finally {
            if ( rset != null) {
                try {
                    rset.close();
                } catch (SQLException ex) {
                    LOGGER.log( Level.SEVERE, ex.toString(), ex );
                }
            }
        }

        return user;
    }


    public boolean insertUser(User user) {

        try (
                Connection conn = this.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            var result = stmt.executeUpdate(String.format("INSERT INTO user (name) VALUES ('%s')", user.getName()));
            LOGGER.log( Level.FINE, "Affected rows: {0}", stmt.getUpdateCount());

            if (result == 1) return true;

        } catch (SQLException ex) {
            LOGGER.log( Level.SEVERE, ex.toString(), ex );
        }

        return false;
    }

    public void addScore(int userId, int score) {

        try (
                Connection conn = this.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(String.format(" UPDATE user    SET score = score + %d, games_played = games_played + 1    WHERE id = %d", score, userId));
            LOGGER.log( Level.FINE, "Affected rows: {0}", stmt.getUpdateCount());

        } catch (SQLException ex) {
            LOGGER.log( Level.SEVERE, ex.toString(), ex );
        }

    }

    public User checkPassword(String name, String password) {
        User user = null;
        ResultSet rset = null;
        try (
                Connection conn = this.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            String strSelect = String.format("select * from user WHERE name = %s AND password=%s;", name, password);
            rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                User p = new User(
                        rset.getInt(userId),
                        rset.getString(userName),
                        rset.getString(userPass)
                );

                LOGGER.log( Level.FINE, "User CheckPassword: {0}" , p);

                user = p;
            }

        } catch (SQLException ex) {
            LOGGER.log( Level.SEVERE, ex.toString(), ex );
        } finally {
            if ( rset != null) {
                try {
                    rset.close();
                } catch (SQLException ex) {
                    LOGGER.log( Level.SEVERE, ex.toString(), ex );
                }
            }
        }

        return user;
    }
}