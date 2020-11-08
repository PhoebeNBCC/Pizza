/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBean;

//import Business.Pizza;
//import Business.Pizza;
import Business.Crust;
import Business.Customer;
import Business.Pizza;
import Business.Size;
import Business.Toppings;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author phoeb
 */
public class PizzaDL {

    private static Connection conn = ConnectionDL.GetConnection();

    //@Inject
    //private Pizza p;
    private Toppings t;

    public PizzaDL() {
        conn = ConnectionDL.GetConnection();
    }

    public static ArrayList<Toppings> FetchActiveToppings() {
        String name, createdate;
        int toppingId, empAddedBy, isActive;
        double price;

        String sql = "select toppingId, name, price, createdate, empAddedBy, isActive from toppings where isActive= ? ";
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

    public static Size getSize(int sizeid) {
        String name;

        String sql = "select * from sizes where sizeid=?";
        try {
            PreparedStatement s = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            s.setInt(1, sizeid);
            ResultSet rs = s.executeQuery();

            //rs.first();//move cursor to the first row
            if (rs.first()) {

                name = rs.getString(2);
                Size b = new Size(sizeid, name);
                return b;
            }
            return null;

        } catch (SQLException ex) {
            //swallow the exception
            return null;
        }
    }

    public static Crust getCrust(int id) {
        String name;
        Crust b = null;
        String sql = "select * from crusttypes where crustTypeId=?";
        try {
            PreparedStatement s = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();

            //rs.first();//move cursor to the first row
            if (rs.first()) {
                name = rs.getString(2);
                b = new Crust(id, name);
                return b;
            }
            return b;

        } catch (SQLException ex) {
            //swallow the exception
            return b;
        }
    }
     public static String toStringTopping(String[] str) {
        String t = "";
        if(str!=null){
            t = String.join(",", str);
        }
        
        return t;
    }

    public static ArrayList<Toppings> getSelectToppings(String[] ids) {
        ArrayList<Toppings> ts = new ArrayList<Toppings>();
        for (String i : ids) {

            ts.add(getToppingById(Integer.parseInt(i)));
        }
        return ts;
    }

    public static Toppings getToppingById(int id) {
        String name, createdate;
        int toppingId, empAddedBy, isActive;
        double price;
        Toppings b = null;
        String sql = "select * from toppings where toppingId=?";
        try {
            PreparedStatement s = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();

            //rs.first();//move cursor to the first row
            if (rs.first()) {
                toppingId = rs.getInt(1);
                name = rs.getString(2);
                price = rs.getDouble(3);
                createdate = rs.getString(4);
                empAddedBy = rs.getInt(5);
                isActive = rs.getInt(6);

                b = new Toppings(toppingId, name, price, createdate, empAddedBy, isActive);
                return b;
            }
            return b;

        } catch (SQLException ex) {
            //swallow the exception
            return b;
        }
    }

    public static double calculatePizza(int size, String[] toppings) {
        double total = 0;
        switch (size) {
            case 1:
                total += 6;
                break;
            case 2:
                total += 10;
                break;
            case 3:
                total += 14;
                break;
            case 4:
                total += 16;
                break;
        }
        if (toppings != null) {
            for (String s : toppings) {
                int toppingId = Integer.parseInt(s);
                total += getPrice(toppingId);
            }
        }

        return total;
    }

    public static double getPrice(int id) {
        double price = 0;
        String sql = "select price from toppings where toppingId=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                price = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PizzaDL.class.getName()).log(Level.SEVERE, null, ex);
            return price;
        }
        return price;
    }

    public static int InsertPizza(Pizza s) {
        int id = 0;
        String sql = "INSERT INTO pizza (sizeId, crustTypeId, price, orderId) VALUES(?, ?, ?, ?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, s.getSizeId());
            stmt.setInt(2, s.getCrustTypeId());
            stmt.setDouble(3, s.getPrice());
            stmt.setInt(4, s.getOrderId());

            if (stmt.executeUpdate() > 0) {
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    id = rs.getInt(1);
                }
                return id;
            } else {
                return id;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return id;
        }
    }

    public static int addPizzaToppingsMap(int pizzaId, String[] toppingIds) {
        int count=0;
        int toppingId = 0;
        if (toppingIds != null) {
            for (String s : toppingIds) {
                toppingId = Integer.parseInt(s);
                String sql = "INSERT INTO pizza_toppings_map (pizzaId, pizza_toppings_id) VALUES(?, ?);";

                try {
                    PreparedStatement stmt = conn.prepareStatement(sql);

                    stmt.setInt(1, pizzaId);
                    stmt.setInt(2, toppingId);

                    if (stmt.executeUpdate() > 0) {
                        count++;   
                    } 
                } catch (SQLException ex) {
                    
                    ex.printStackTrace(); 
                    return count;
                }
            }
            
        }
        return count;

    }

}
