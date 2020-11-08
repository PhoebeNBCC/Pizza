/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DataBean.PizzaDL;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author phoeb
 */
@SessionScoped
public class Pizza implements Serializable{
    @Inject
    private PizzaDL bean;
    private int pizzaId;
    private int sizeId;
    private int isFinished;//1 is finished, 0 means not finished
    private int crustTypeId;
    private double price;
    private int orderId;
    private String[] toppings;
    //private String pizzapizzacol;

    public String[] getToppings() {
        return toppings;
    }

    public void setToppings(String[] toppings) {
        this.toppings = toppings;
    }

    public Pizza() {
    }

    public Pizza(int pizzaId, int sizeId, int isFinished, int crustTypeId, double price, int orderId) {
        this.pizzaId = pizzaId;
        this.sizeId = sizeId;
        this.isFinished = isFinished;
        this.crustTypeId = crustTypeId;
        this.price = price;
        this.orderId = orderId;
    }

    

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public int getCrustTypeId() {
        return crustTypeId;
    }

    public void setCrustTypeId(int crustTypeId) {
        this.crustTypeId = crustTypeId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    

    


}
