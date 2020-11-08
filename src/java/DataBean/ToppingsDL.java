/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBean;

import Business.Toppings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author phoeb
 */
@Stateless
public class ToppingsDL {
    private static Connection conn = ConnectionDL.GetConnection();
@Inject
Toppings t;

    public ToppingsDL() {
        conn = ConnectionDL.GetConnection();
    }
    public static ArrayList<Toppings> FetchActiveToppings() {
        String name, createdate;
        int toppingId, empAddedBy, isActive;
        double price;

        String sql = "select toppingId, name, price, createdate, empAddedBy, isActive from toppings where isActive=? ";
        int id = 0;
        try {
            PreparedStatement s = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            s.setInt(1, 1);
            ArrayList<Toppings> activeToppingsList = new ArrayList<>();
            ResultSet rs = s.executeQuery();

            rs.first();//move cursor to the first row
            do {
                toppingId = rs.getInt(1);
                name = rs.getString(2);
                price = rs.getDouble(3);
                createdate = rs.getString(4);
                empAddedBy = rs.getInt(5);
                isActive = rs.getInt(6);

                Toppings b = new Toppings(toppingId, name, price, createdate, empAddedBy, isActive);
                //this injection doesn't work because it always inserts the last record in the resultset
                activeToppingsList.add(b);
            } while (rs.next());
            return activeToppingsList;

        } catch (SQLException ex) {
            //swallow the exception
        }
        //only gets here if an error occurs
        return null;

    }
    public static ArrayList<Toppings> FetchAllToppings() {
        String name, createdate;
        int toppingId, empAddedBy, isActive;
        double price;

        String sql = "select toppingId, name, price, createdate, empAddedBy, isActive from toppings ";
        int id = 0;
        try {
            PreparedStatement s = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ArrayList<Toppings> activeToppingsList = new ArrayList<>();
            ResultSet rs = s.executeQuery();

            rs.first();//move cursor to the first row
            do {
                toppingId = rs.getInt(1);
                name = rs.getString(2);
                price = rs.getDouble(3);
                createdate = rs.getString(4);
                empAddedBy = rs.getInt(5);
                isActive = rs.getInt(6);

                Toppings b = new Toppings(toppingId, name, price, createdate, empAddedBy, isActive);
                //this injection doesn't work because it always inserts the last record in the resultset
                activeToppingsList.add(b);
            } while (rs.next());
            return activeToppingsList;

        } catch (SQLException ex) {
            //swallow the exception
        }
        //only gets here if an error occurs
        return null;

    }

    public static boolean addTopping(Toppings s) {
        String sql = "INSERT INTO toppings (name, price, empAddedBy) VALUES(?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, s.getName());
            stmt.setDouble(2, s.getPrice());
            stmt.setInt(3, 1);

            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean inActiveToppings(int id) {
        String sql = "UPDATE toppings SET isActive= 0 WHERE toppingId= ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            

            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static boolean activeToppings(int id) {
        String sql = "UPDATE toppings SET isActive = 1 WHERE toppingId = ? ";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
   public static String displayToppings(ArrayList<Toppings> ts) {
        String strToppings = "";
        if (ts != null) {
            for (Toppings t : ts) {
                strToppings += ", " + t.getName();
            }
        }

        return strToppings;
    }

    public static String toStringTopping(String[] str) {
        String t = "";
        if(str!=null){
            t = String.join(",", str);
        }
        
        return t;
    }
    public static String displayToppingName(String[] toppingIds){
        String result="";
        if(toppingIds!=null){
            for(String s: toppingIds){
                result+=getToppingsById(Integer.parseInt(s)).getName() + " - ";
            }
        }
        return result;
    }
    public static Toppings getToppingsById(int id) {
        String name, createdate;
        int toppingId, empAddedBy, isActive;
        double price;
        Toppings b = null;
        String sql = "select toppingId, name, price, createdate, empAddedBy, isActive from toppings where toppingId=? ";
        
        try {
            PreparedStatement s = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            s.setInt(1, id);
            
            ResultSet rs = s.executeQuery();

            if(rs.first())//move cursor to the first row
            {
                toppingId = rs.getInt(1);
                name = rs.getString(2);
                price = rs.getDouble(3);
                createdate = rs.getString(4);
                empAddedBy = rs.getInt(5);
                isActive = rs.getInt(6);

                 b = new Toppings(toppingId, name, price, createdate, empAddedBy, isActive);
                //this injection doesn't work because it always inserts the last record in the resultset
                
            } 
            return b;

        } catch (SQLException ex) {
            //swallow the exception
            return b;
        }
        //only gets here if an error occurs
        

    }
}
