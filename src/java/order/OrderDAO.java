/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import util.DBHelper;
import util.RandomStringUtil;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class OrderDAO implements Serializable {
    
    public boolean insertOrder(String orderID)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. create sql string
                String sql = "INSERT INTO [Order] (id, orderDate, total) "
                        + "VALUES (?, GETDATE(), 0);";
                //3. create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
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
    
    public String createOrderID() {
        return RandomStringUtil.randomAlphaNumeric(15);
    }
}
