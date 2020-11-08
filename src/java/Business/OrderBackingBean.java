/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DataBean.OrderDL;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author phoeb
 */
@Named(value = "orderBackingBean")
@ViewScoped
public class OrderBackingBean implements Serializable{
    @Inject
    private Order o;
    @Inject 
    OrderDL bean;

    public OrderBackingBean() {
    }

    public Order getO() {
        return o;
    }

    public void setO(Order o) {
        this.o = o;
    }
    
    public int addOrder(){
        return bean.addOrder(o);
    }
    public static int addOrder(Order o){
        return OrderDL.addOrder(o);
    }
    public static String updateDeliveryDateTime(int orderId){
        return OrderDL.updateDeliveryDateTime(orderId);
    }
    public ArrayList<Order> FetchAllOrders(){
        return bean.FetchAllOrders();
    }
    public  ArrayList<Order> FetchPendingOrders(){
        return bean.FetchPendingOrders();
    }
    public  ArrayList<Order> FetchFilledOrders(){
        return bean.FetchFilledOrders();
    }
    public String updateOrderStatus(int id){
        if(bean.updateOrderStatus(id)){
            return "Admin_order.xhtml";//refresh page
        }
        else{
            return "";
        }
    }
    public String getServlet(){
        
        return "OrderProcUpdateStatus?id=";//refresh page
       
    }
    
    
}
