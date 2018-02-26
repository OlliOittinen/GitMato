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

    public static void main(String args[]) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mariadb://10.114.34.14:3306/DB?user=Olli&password=laiskajaakko");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from highscore");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
