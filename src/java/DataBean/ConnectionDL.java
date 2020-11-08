/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author phoeb
 */
public class ConnectionDL {
    
    private Connection conn;
    
    public static java.sql.Connection GetConnection() {
        Connection conn = null;
        String dbURL = "jdbc:mysql://localhost:3306/pizzadb";
        String username = "root";
        String password = "";//blank password for root
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
}
