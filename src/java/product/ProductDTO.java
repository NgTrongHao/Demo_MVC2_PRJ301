/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class ProductDTO implements Serializable {

    private String productID;
    private String name;
    private int quantity;
    private float unitPrice;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(String productID, String name, int quantity, float unitPrice, boolean status) {
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.status = status;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}