/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Eero, Olli
 */
public class DBConnection {

    String name;
    int score;
    Connection con = null;
    ArrayList<String> scores = new ArrayList();
    String highscore = "";
    int indexOf;

    public DBConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mariadb://localhost:4444/score", "Olli", "laiskajaakko");
        } catch (SQLException e) {
            System.out.println(e);
           // e.printStackTrace();
        }
    }

    public ArrayList<String> showHighscore(String pelimoodi) {
        try {
            PreparedStatement query = null;
            try {
                query = con.prepareStatement("select * from highscore where pelimuoto='" + pelimoodi + "' order by pisteet desc");
                ResultSet result = query.executeQuery();
                try {
                    while (result.next()) {
                        scores.add(result.getString("nimi") + " " + result.getInt("pisteet"));
                    }
                    return scores;
                    /*
                    int i = 0;
                    for (String s : scores) {
                        i++;
                        if (i <= 10) {
                            highscore += +(i) + ". " + s + '\n';
                        }
                    }
                    indexOf = scores.indexOf(name + " " + score) + 1;

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Highscore");
                    alert.setHeaderText("Top 10\n You placed: #" + indexOf + " with " + score + " points!");
                    alert.setContentText(highscore);
                    alert.showAndWait();

                    scores.clear();
                    highscore = "";
                    */
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

    public void submitScore(int score, String name, String pelimoodi) {
        this.score = score;
        this.name = name;
        try {
            PreparedStatement query = null;
            try {
                query = con.prepareStatement("INSERT INTO highscore VALUES(?, ?, ?)");
                query.setString(1, name);
                query.setInt(2, score);
                query.setString(3, pelimoodi);
                //System.out.println(query);
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
