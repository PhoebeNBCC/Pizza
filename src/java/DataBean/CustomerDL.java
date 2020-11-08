/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBean;

import Business.Customer;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author phoeb
 */
public class CustomerDL {

    private static Connection conn = ConnectionDL.GetConnection();
    private Customer c;

    public CustomerDL() {
        conn = ConnectionDL.GetConnection();
    }

    public static int addCustomer(Customer s) {
        int id = 0;
        String sql = "INSERT INTO customer (firstName, lastName, phoneNumber, email, houseNumber, street, province, postalCode) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, s.getfName());
            stmt.setString(2, s.getlName());
            stmt.setString(3, s.getPhone());
            stmt.setString(4, s.getEmail());
            stmt.setInt(5, s.getHouseNo());
            stmt.setString(6, s.getStreet());
            stmt.setString(7, s.getProvince());
            stmt.setString(8, s.getPostalCode());

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
    public static Customer getCustomerById(int id){
        String sql = "select customerid, firstName, lastName, phoneNumber, email, houseNumber, street, province, postalCode from customer where customerid= ? ";
        int customerid, houseNumber;
        String firstname, lastname, phone, email, street, province, postalCode;
        try {
            PreparedStatement s = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            
            //rs.first();//move cursor to the first row
            if(rs.first()) {
                customerid = rs.getInt(1);
                firstname = rs.getString(2);
                lastname = rs.getString(3);
                phone = rs.getString(4);
                email = rs.getString(5);
                houseNumber = rs.getInt(6);
                street = rs.getString(7);
                province = rs.getString(8);
                postalCode = rs.getString(9);

                Customer b = new Customer(firstname, lastname, phone, email, houseNumber, street, province, postalCode);
                b.setCustomerid(customerid);
                return b;
            } 
            

        } catch (SQLException ex) {
            //swallow the exception
            return null;
        }
        return null;
        
    }
}
