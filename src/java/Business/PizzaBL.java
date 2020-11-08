/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DataBean.PizzaDL;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author phoeb
 */

public class PizzaBL implements Serializable{

    private Pizza pizza;
    private String msg;
    private double total=0;

    public PizzaBL() {
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    

    
    public static Size getSize(int sizeid){
       return PizzaDL.getSize(sizeid);
    }
    public static Crust getCrust(int id){
       return PizzaDL.getCrust(id);
    }
    //afraid to change becasu it is in jsp
    
    public static int InsertPizza(Pizza s){
        return PizzaDL.InsertPizza(s);
    }
    public static int addPizzaToppingsMap(int pizzaId, String[] toppingIds){
        return PizzaDL.addPizzaToppingsMap(pizzaId, toppingIds);
    }
    public static double calculatePizza(int size, String[] toppings){
        return PizzaDL.calculatePizza(size, toppings);
    }
    
    
}
