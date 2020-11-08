/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DataBean.CustomerDL;

/**
 *
 * @author phoeb
 */
public class CustomerBL {
    private Customer c;
    public static int addCustomer(Customer s){
        return CustomerDL.addCustomer(s);
    }
    public static Customer getCustomerById(int id){
        return CustomerDL.getCustomerById(id);
    }
}
