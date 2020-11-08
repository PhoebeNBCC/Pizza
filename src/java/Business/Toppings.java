/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author phoeb
 */
@ViewScoped
public class Toppings implements Serializable{

    private int toppingId;
    private String name;
    private double price;
    private String createdate;
    private int empAddedBy;
    private int isActive;

    public Toppings() {
    }

    public Toppings(int toppingId, String name, double price, String createdate, int empAddedBy, int isActive) {
        this.toppingId = toppingId;
        this.name = name;
        this.price = price;
        this.createdate = createdate;
        this.empAddedBy = empAddedBy;
        this.isActive = isActive;
    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public int getEmpAddedBy() {
        return empAddedBy;
    }

    public void setEmpAddedBy(int empAddedBy) {
        this.empAddedBy = empAddedBy;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    

}
