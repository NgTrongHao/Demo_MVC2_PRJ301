/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import util.DBHelper;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class ProductDAO implements Serializable {

    private ProductCart products;

    public ProductCart getProducts()
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "SELECT productID, name, quantity, unitPrice, status "
                        + "FROM Product";
                //3. create Statement Object
                stm = con.prepareStatement(sql);
                //4. execute querry
                rs = stm.executeQuery();
                //5. process
                while (rs.next()) {
                    //5.1 get data from rs
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    float unitPrice = rs.getFloat("unitPrice");
                    boolean status = rs.getBoolean("status");
                    //5.2 set data into DTO
                    ProductDTO dto = new ProductDTO(productID, name, quantity, unitPrice, status);
                    //5.3 add dto into List
                    if (this.products == null) {
                        this.products = new ProductCart();
                    }
                    this.products.addProduct(productID, dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return products;
    }

    public boolean decreaseProductQuantityByAddToCart(String productID, int quantity)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBHelper.createConnection();
            if (con != null) {
                String sql = "UPDATE Product "
                        + "SET quantity = ? "
                        + "WHERE productID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, this.products.getProductByID(productID).getQuantity() - quantity);
                stm.setString(2, productID);
                int effectiveRows = stm.executeUpdate();
                if (effectiveRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
