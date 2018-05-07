/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Eero, Olli
 */
class DBConnection {

    private Connection con = null;
    private ArrayList<String> scores = new ArrayList<>();
    String highscore = "";

    Connection getCon() {
        return con;
    }

    int indexOf;

    /**
     * Class constructor.
     */
    DBConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mariadb://localhost:4444/score", "Olli", "laiskajaakko");
        } catch (SQLException e) {
            System.out.println(e);
            // e.printStackTrace();
        }
    }

    /**
     * Returns the high scores from the database, sorted in descending order.
     *
     * @param gameMode the game mode that was played
     * @return an ArrayList based on the gameMode played
     */
    public ArrayList<String> showHighscore(String gameMode) {

        try {
            PreparedStatement query = null;
            try {
                query = con.prepareStatement("select * from highscore where pelimuoto='" + gameMode + "' order by pisteet desc");
                ResultSet result = query.executeQuery();
                try {
                    while (result.next()) {
                        scores.add(result.getString("nimi") + " " + result.getInt("pisteet"));
                    }
                    return scores;
                } catch (SQLException e) {
                    do {
                        System.err.println("Viesti: " + e.getMessage());
                        System.err.println("Koodi: " + e.getErrorCode());
                        System.err.println("SQL-tilakoodi: " + e.getSQLState());
                    } while (e.getNextException() != null);
                } finally {
                    result.close();
                    //System.out.println("Tulosjoukko suljettu.");
                }
            } catch (Exception e) {
                System.out.println("Epäonnistui. ");
            } finally {
                try {
                    query.close();
                    //System.out.println("Kysely suljettu");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Submits the user's score to the database after the game
     *
     * @param score    the user's score
     * @param name     name to be stored in the database
     * @param gameMode game mode that was played
     */
    public void submitScore(int score, String name, String gameMode) {
        int score1 = score;
        String name1 = name;
        try {
            PreparedStatement query = null;
            try {
                query = con.prepareStatement("INSERT INTO highscore VALUES(?, ?, ?)");
                query.setString(1, name);
                query.setInt(2, score);
                query.setString(3, gameMode);
                ResultSet result = query.executeQuery();
                try {
                    while (result.next()) {
                    }
                } catch (SQLException e) {
                    do {
                        System.err.println("Viesti: " + e.getMessage());
                        System.err.println("Koodi: " + e.getErrorCode());
                        System.err.println("SQL-tilakoodi: " + e.getSQLState());
                    } while (e.getNextException() != null);
                } finally {
                    result.close();
                    //System.out.println("Tulosjoukko suljettu.");
                }
            } catch (Exception e) {
                System.out.println("Epäonnistui. ");
                e.printStackTrace();
            } finally {
                try {
                    query.close();
                    //System.out.println("Kysely suljettu");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
