/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderDetail;

import java.io.Serializable;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class OrderDetailDTO implements Serializable {

    private int id;
    private String productID;
    private int quantity;
    private float price;
    private float total;
    private String orderID;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int id, String productID, int quantity, float price, float total, String orderID) {
        this.id = id;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.orderID = orderID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

}
