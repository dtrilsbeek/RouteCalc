package database;

import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private static String host = "studmysql01.fhict.local";
    private static String user = "dbi391176";
    private static String dbName = "dbi391176";
    private static String pass = "appelsenperen12";
    private static int port = 3306;
    private static String idPlayer = "id";
    private static String namePlayer = "name";
    private static String scorePlayer = "score";
    private static String gamesPlayedPlayer = "games_played";
    private static final Logger LOGGER = Logger.getLogger( Database.class.getName() );

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://" + host + ":"+port+"/"+dbName,
                user, pass);   // For MySQL only
    }

    public List<PlayerDTO> getAllPlayers() {

        List<PlayerDTO> players = new ArrayList<>();
        ResultSet rset = null;

        try (
                Connection conn = this.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            String strSelect = "select * from player";
            rset = stmt.executeQuery(strSelect);

            LOGGER.log( Level.FINEST, "The records selected are:");
            int rowCount = 0;
            while (rset.next()) {

                PlayerDTO p = new PlayerDTO(
                        rset.getInt(idPlayer),
                        rset.getString(namePlayer),
                        rset.getInt(scorePlayer),
                        rset.getInt(gamesPlayedPlayer)
                );

                players.add(p);

                LOGGER.log( Level.FINE, "playerDTO GetAll: {0}" , p);
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

        return players;
    }

    public PlayerDTO getPlayerById(int id) {

        PlayerDTO player = null;
        ResultSet rset = null;

        try (
                Connection conn = this.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            String strSelect = String.format("select * from player WHERE id = %d;", id);
            rset = stmt.executeQuery(strSelect);

            LOGGER.log( Level.FINEST, "The records selected are:");

            while (rset.next()) {

                PlayerDTO p = new PlayerDTO(
                        rset.getInt(idPlayer),
                        rset.getString(namePlayer),
                        rset.getInt(scorePlayer),
                        rset.getInt(gamesPlayedPlayer)
                );

                LOGGER.log( Level.FINE, "playerDTO GetById: {0}" , p);

                player = p;
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

        return player;
    }


    public void insertPlayer(PlayerDTO player) {

        try (
                Connection conn = this.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(String.format("INSERT INTO player (name) VALUES ('%s')", player.getName()));
            LOGGER.log( Level.FINE, "Affected rows: {0}", stmt.getUpdateCount());

        } catch (SQLException ex) {
            LOGGER.log( Level.SEVERE, ex.toString(), ex );
        }

    }

    public void addScore(int playerId, int score) {

        try (
                Connection conn = this.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(String.format(" UPDATE player    SET score = score + %d, games_played = games_played + 1    WHERE id = %d", score, playerId));
            LOGGER.log( Level.FINE, "Affected rows: {0}", stmt.getUpdateCount());

        } catch (SQLException ex) {
            LOGGER.log( Level.SEVERE, ex.toString(), ex );
        }

    }

    public PlayerDTO checkPassword(String name, String password) {
        PlayerDTO player = null;
        ResultSet rset = null;
        try (
                Connection conn = this.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            String strSelect = String.format("select * from player WHERE name = %s AND password=%s;", name, password);
            rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                PlayerDTO p = new PlayerDTO(
                        rset.getInt(idPlayer),
                        rset.getString(namePlayer),
                        rset.getInt(scorePlayer),
                        rset.getInt(gamesPlayedPlayer)
                );

                LOGGER.log( Level.FINE, "playerDTO CheckPassword: {0}" , p);

                player = p;
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

        return player;
    }
}