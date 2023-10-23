/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class OrderDTO implements Serializable {

    private String id;
    private Date orderDate;
    private float total;

    public OrderDTO() {
    }

    public OrderDTO(String id, Date orderDate, float total) {
        this.id = id;
        this.orderDate = orderDate;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
