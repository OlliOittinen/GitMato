/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;

/**
 *
 * @author Eero
 */
public class DBConnection {

    Highscore hscore = new Highscore();
    int points = hscore.getHighscore();
    String name = hscore.getName();
    Connection con = null;

    public DBConnection() {
        try {
        con = DriverManager.getConnection("jdbc:mariadb://localhost:4444/score", "Olli", "laiskajaakko");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void showHighscore(){
        try {
            PreparedStatement query = null;
            try {
                query = con.prepareStatement("select * from highscore order by pisteet desc limit 10");
                ResultSet result = query.executeQuery();
                try {
                    while (result.next()) {
                        System.out.println("Nimi: " + result.getString("nimi") + ", Pisteet: " + result.getInt("pisteet"));
                    }
                } catch (SQLException e) {
                    do {
                        System.err.println("Viesti: " + e.getMessage());
                        System.err.println("Koodi: " + e.getErrorCode());
                        System.err.println("SQL-tilakoodi: " + e.getSQLState());
                    } while (e.getNextException() != null);
                } finally {
                    result.close();
                    System.out.println("Tulosjoukko suljettu.");
                }
            } catch (Exception e) {
                System.out.println("Epäonnistui. ");
            } finally {
                try {
                    query.close();
                    System.out.println("Kysely suljettu");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void submitScore(int score, String name){
         try {
            PreparedStatement query = null;
            try {
                query = con.prepareStatement("INSERT INTO highscore VALUES('"+name+"', '"+score+"')");
                ResultSet result = query.executeQuery();
                try {
                    while (result.next()) {
                        System.out.println("Nimi: " + result.getString("nimi") + ", Pisteet: " + result.getInt("pisteet"));
                    }
                } catch (SQLException e) {
                    do {
                        System.err.println("Viesti: " + e.getMessage());
                        System.err.println("Koodi: " + e.getErrorCode());
                        System.err.println("SQL-tilakoodi: " + e.getSQLState());
                    } while (e.getNextException() != null);
                } finally {
                    result.close();
                    System.out.println("Tulosjoukko suljettu.");
                }
            } catch (Exception e) {
                System.out.println("Epäonnistui. ");
            } finally {
                try {
                    query.close();
                    System.out.println("Kysely suljettu");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
