/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import util.DBHelper;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class OrderDetailDAO implements Serializable {

    public boolean insertOrderDetail(int id, String productID, int quantity, String orderID)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. create sql string
                String sql = "INSERT INTO [OrderDetail] (id, productId, quantity, orderId) "
                        + "VALUES (?, ?, ?, ?)";
                //3. create statement object
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setString(2, productID);
                stm.setInt(3, quantity);
                stm.setString(4, orderID);
                //4. execute query
                int effectiveRows = stm.executeUpdate();
                //5. process
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
