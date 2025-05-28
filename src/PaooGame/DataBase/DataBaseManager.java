package PaooGame.DataBase;

import PaooGame.PublicGameData;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseManager {

    private static DataBaseManager instance;  // instanța unică (statică)
    private Connection conn; // conexiunea SQL

    // Constructorul este privat, deci nu poate fi instanțiat din exterior
    private DataBaseManager() {}

    // Metoda publică de acces la instanță (cu lazy instantiation)
    public static synchronized DataBaseManager getInstance() {
        if (instance == null) {
            instance = new DataBaseManager();
        }
        return instance;
    }

    // Conectare la baza de date SQLite
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC"); // Încarcă driverul
            conn = DriverManager.getConnection("jdbc:sqlite:game2.db");
            System.out.println("Conexiune la baza de date reușită!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC SQLite nu a fost găsit!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Eroare conexiune la baza de date: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS players ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "score INTEGER, "
                + "level INTEGER, "
                + "character TEXT, "
                + "finishedLevel3 INTEGER DEFAULT 0, "
                + "posX INTEGER DEFAULT 0, "
                + "posY INTEGER DEFAULT 0, "
                + "timestamp DATETIME DEFAULT (DATETIME('now', 'localtime'))"
                + ");";


        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabel creat cu succes!" );
        } catch (SQLException e) {
            System.out.println("Eroare creare tabel: " + e.getMessage());
        }
    }

    public void savePlayer(String name, int score, int level, String character, int posX, int posY)
    {
        String checkQuery = "SELECT * FROM players WHERE name = ?";
        String updateQuery = "UPDATE players SET score = ?, level = ?, character = ?,posX = ?, posY = ?, timestamp = DATETIME('now', 'localtime') WHERE name = ?";
        String insertQuery = "INSERT INTO players(name, score, level, character, posX, posY) VALUES(?,?,?,?,?,?)";
        try(PreparedStatement checkStmt = conn.prepareStatement(checkQuery))
        {
            checkStmt.setString(1,name);
            ResultSet rs = checkStmt.executeQuery();

            if(rs.next())
            {
                // exista deja => facem update
                try(PreparedStatement updateStmt = conn.prepareStatement(updateQuery))
                {
                    updateStmt.setInt(1, score);
                    updateStmt.setInt(2, level);
                    updateStmt.setString(3, character);
                    updateStmt.setInt(4, posX);
                    updateStmt.setInt(5, posY);
                    updateStmt.setString(6, name);
                    updateStmt.executeUpdate();
                }
            }
            else
            {
                // nu exista => il adaugam
                try (PreparedStatement pstmt = conn.prepareStatement(insertQuery))
                {
                    pstmt.setString(1, name);
                    pstmt.setInt(2, score);
                    pstmt.setInt(3, level);
                    pstmt.setString(4, character);
                    pstmt.setInt(5, posX);
                    pstmt.setInt(6, posY);
                    pstmt.executeUpdate();
                }
            }
        }catch (SQLException e) {
            System.out.println("Eroare la salvare: " + e.getMessage());
        }
    }

    public ArrayList<String> getTopPlayers(int limit) {
        ArrayList<String> top = new ArrayList<>();
        String sql = "SELECT name, score FROM players WHERE finishedLevel3 = 1 ORDER BY score DESC LIMIT ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                top.add(rs.getString("name") + " - " + rs.getInt("score") + " puncte");
            }
        } catch (SQLException e) {
            System.out.println("Eroare top scoruri: " + e.getMessage());
        }

        return top;
    }

    public boolean loadPlayerByName(String name)
    {
        String sql = "SELECT * FROM players WHERE name = ? ORDER BY timestamp DESC LIMIT 1";

        try(PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
            {
                PublicGameData.playerName = rs.getString("name");
                PublicGameData.score = rs.getInt("score");
                PublicGameData.currentLevel = rs.getInt("level");
                PublicGameData.characterType = rs.getString("character");
                PublicGameData.playerPosX = rs.getInt("posX");
                PublicGameData.playerPosY = rs.getInt("posY");
                return true;
            }
        }catch(SQLException e)
        {
            System.out.println("Eroare la incarcare player dupa nume: " + e.getMessage());
        }

        return false;
    }

    public void markLevel3Finished(String name) {
        String sql = "UPDATE players SET finishedLevel3 = 1, timestamp = DATETIME('now', 'localtime') WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Eroare la marcare finalizare nivel 3: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Eroare la închidere: " + e.getMessage());
        }
    }
}
