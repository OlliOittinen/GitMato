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

    public static void main(String args[]) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:4444/score", "Olli", "laiskajaakko");
            PreparedStatement query = null;
            PreparedStatement query2 = null;
            try {
                query = con.prepareStatement("select * from highscore");
                query2 = con.prepareStatement("INSERT INTO highscore VALUES('name',points)");
                query2.executeQuery();
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
                    query2.close();
                    System.out.println("Kysely suljettu");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
