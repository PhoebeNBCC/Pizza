/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBean;

import Business.Customer;
import Business.Order;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author phoeb
 */
@Stateless
public class OrderDL {

    private static Connection conn = ConnectionDL.GetConnection();
    @Inject
    Order o;

    public OrderDL() {
        conn = ConnectionDL.GetConnection();
    }

    public static int addOrder(Order s) {

        int id = 0;
        String sql = "INSERT INTO orders (totalPrice, customerId) VALUES(?, ?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setDouble(1, s.getTotalPrice());
            stmt.setInt(2, s.getCustomerId());
            if (stmt.executeUpdate() > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                return id;
            } else {
                return id;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return id;
        }
    }

    public static String getDeliveryDateTime(int orderId) {
        String strplacedDateTime;

        String sql = "select placedDateTime from orders where orderId=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                strplacedDateTime = rs.getString(1);
                strplacedDateTime=strplacedDateTime.replace(" ", "T");
                if(strplacedDateTime.length()>18){
                    strplacedDateTime=strplacedDateTime.substring(0, 19);
                }
                LocalDateTime placedDateTime = LocalDateTime.parse(strplacedDateTime);
                LocalDateTime deliveryDateTime = placedDateTime.plus(1, ChronoUnit.HOURS);
                String strdeliveryDateTime = deliveryDateTime.toString();
                if(strdeliveryDateTime.length()>18){
                    strdeliveryDateTime=strdeliveryDateTime.substring(0, 19);
                }
                return strdeliveryDateTime;
            }
        } catch (SQLException ex) {

            return "";
        }
        return "";
    }

    public static String updateDeliveryDateTime(int orderId) {
        String strdeliveryDateTime = getDeliveryDateTime(orderId);
        if (!strdeliveryDateTime.isEmpty()) {
            String sql = "update orders set deliveryDateTime=? where orderId=?";
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, strdeliveryDateTime);
                stmt.setInt(2, orderId);
                if (stmt.executeUpdate() > 0) {
                    return strdeliveryDateTime;
                }

            } catch (SQLException ex) {

                return "";
            }
            
        }
        return "";

    }
    public static ArrayList<Order> FetchAllOrders() {
        String deliveryDateTime, placedDateTime, orderStatus;
        int customerId, orderId;
        double totalPrice;

        String sql = "select orderId, totalPrice, deliveryDateTime, placedDateTime, customerId, orderStatus from orders";
        int id = 0;
        try {
            PreparedStatement s = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ArrayList<Order> orderList = new ArrayList<>();
            ResultSet rs = s.executeQuery();

            rs.first();//move cursor to the first row
            do {
                orderId = rs.getInt(1);
                totalPrice = rs.getDouble(2);
                deliveryDateTime = rs.getString(3);
                placedDateTime = rs.getString(4);
                customerId = rs.getInt(5);
                orderStatus = rs.getString(6);

                Order b = new Order(orderId, totalPrice, deliveryDateTime, placedDateTime, customerId, orderStatus);
                //this injection doesn't work because it always inserts the last record in the resultset
                orderList.add(b);
            } while (rs.next());
            return orderList;

        } catch (SQLException ex) {
            //swallow the exception
        }
        //only gets here if an error occurs
        return null;

    }
    public static ArrayList<Order> FetchPendingOrders() {
        String deliveryDateTime, placedDateTime, orderStatus;
        int customerId, orderId;
        double totalPrice;

        String sql = "select orderId, totalPrice, deliveryDateTime, placedDateTime, customerId, orderStatus from orders where orderStatus = ?";
        int id = 0;
        try {
            PreparedStatement s = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            s.setString(1, "PENDING");
            ArrayList<Order> orderList = new ArrayList<>();
            ResultSet rs = s.executeQuery();

            rs.first();//move cursor to the first row
            do {
                orderId = rs.getInt(1);
                totalPrice = rs.getDouble(2);
                deliveryDateTime = rs.getString(3);
                placedDateTime = rs.getString(4);
                customerId = rs.getInt(5);
                orderStatus = rs.getString(6);

                Order b = new Order(orderId, totalPrice, deliveryDateTime, placedDateTime, customerId, orderStatus);
                //this injection doesn't work because it always inserts the last record in the resultset
                orderList.add(b);
            } while (rs.next());
            return orderList;

        } catch (SQLException ex) {
            //swallow the exception
        }
        //only gets here if an error occurs
        return null;

    }
    public static ArrayList<Order> FetchFilledOrders() {
        String deliveryDateTime, placedDateTime, orderStatus;
        int customerId, orderId;
        double totalPrice;

        String sql = "select orderId, totalPrice, deliveryDateTime, placedDateTime, customerId, orderStatus from orders where orderStatus != ?";
        int id = 0;
        try {
            PreparedStatement s = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            s.setString(1, "PENDING");
            ArrayList<Order> orderList = new ArrayList<>();
            ResultSet rs = s.executeQuery();

            rs.first();//move cursor to the first row
            do {
                orderId = rs.getInt(1);
                totalPrice = rs.getDouble(2);
                deliveryDateTime = rs.getString(3);
                placedDateTime = rs.getString(4);
                customerId = rs.getInt(5);
                orderStatus = rs.getString(6);

                Order b = new Order(orderId, totalPrice, deliveryDateTime, placedDateTime, customerId, orderStatus);
                //this injection doesn't work because it always inserts the last record in the resultset
                orderList.add(b);
            } while (rs.next());
            return orderList;

        } catch (SQLException ex) {
            //swallow the exception
        }
        //only gets here if an error occurs
        return null;

    }

    public static boolean updateOrderStatus(int id) {
        String sql = "UPDATE orders SET orderStatus = 'Filled' WHERE orderId = ?";

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

}
