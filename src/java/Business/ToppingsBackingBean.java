/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DataBean.OrderDL;
import DataBean.ToppingsDL;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author phoeb
 */
@Named(value = "toppingsBackingBean")
@ManagedBean
@ViewScoped
public class ToppingsBackingBean implements Serializable{
    @Inject
    private Toppings t;
    @Inject 
    ToppingsDL bean;
    

    public Toppings getT() {
        return t;
    }

    public void setT(Toppings t) {
        this.t = t;
    }

    

    public ToppingsBackingBean() {
    }
    //afraid to change becasu it is in jsp
    public static ArrayList<Toppings> FetchActiveToppings(){
        return ToppingsDL.FetchActiveToppings();
    }
    public Toppings getToppingsById(int id){
        return bean.getToppingsById(id);
    }
    //afraid to change becasu it is in jsp
    public static String displayToppingName(String[] toppingIds){
        return ToppingsDL.displayToppingName(toppingIds);
    }
    //for Admin_topping
    public ArrayList<Toppings> FetchAllToppings(){
        return bean.FetchAllToppings();
    }
    public String addTopping(){
        if(bean.addTopping(t)){
            return "Admin_topping.xhtml";
        }
        else {
            return "";
        }
    }
    public String inActiveToppings(int id){
        if(bean.inActiveToppings(id)){
            return "Admin_topping.xhtml";
        }
        else {
            return "";
        }
    }
    public String activeToppings(int id){
        if(bean.activeToppings(id)){
            return "Admin_topping.xhtml";
        }
        else {
            return "";
        }
    }
}
