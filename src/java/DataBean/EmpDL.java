/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBean;

import Business.Employee;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author phoeb
 */
@Stateless
public class EmpDL implements Serializable{
    private static Connection conn;
    @Inject
    private Employee e;

    

    public EmpDL() {
        conn = ConnectionDL.GetConnection();
    }
    public static boolean isValidate(Employee e) {
        String sql = "select * from employee where username=? and password=?";
        int id = 0;
        try {
            PreparedStatement s = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            s.setString(1, e.getUsername());
            s.setString(2, e.getPassword());
            ResultSet rs = s.executeQuery();

            if (rs.first()) {
                return true;
            }//move cursor to the first row

        } catch (SQLException ex) {
            //swallow the exception
        }
        //only gets here if an error occurs
        return false;

    }
    
}
