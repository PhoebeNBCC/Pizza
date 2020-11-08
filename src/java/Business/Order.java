/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.faces.view.ViewScoped;

/**
 *
 * @author phoeb
 */
@ViewScoped
public class Order implements Serializable{
    private int orderId;
    private double totalPrice;
    private String deliveryDateTime;
    private String placedDateTime;
    private int customerId;
    private String orderStatus;

    public Order() {
    }

    public Order(int orderId, double totalPrice, String deliveryDateTime, String placedDateTime, int customerId, String orderStatus) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.deliveryDateTime = deliveryDateTime;
        this.placedDateTime = placedDateTime;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(String deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }

    public String getPlacedDateTime() {
        return placedDateTime;
    }

    public void setPlacedDateTime(String placedDateTime) {
        this.placedDateTime = placedDateTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    
    
}
