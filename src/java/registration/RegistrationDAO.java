/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import util.DBHelper;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class RegistrationDAO implements Serializable {

//    public boolean checkLogin(String username, String password)
//            throws SQLException, ClassNotFoundException, NamingException {
    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
//        boolean result = false;
        RegistrationDTO result = null;

        try {
            //1.Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "SELECT lastName, isAdmin "
                        + "FROM Registration "
                        + "WHERE username = ? "
                        + "AND password = ?";
                //3.Create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.Execute Querry
                rs = stm.executeQuery();
                //5.Process
                if (rs.next()) {
                    //mapping
                    //5.1 get data from result set
                    String fullName = rs.getString("lastName");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data to DTO
                    result = new RegistrationDTO(username, null, fullName, role);   //business kkong luu password user
                }//end username and password are existed
            }//end connection is available
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
        return result;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastName(String searchValue)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE lastname Like ?";
                //3.Create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4.Execute Querry
                rs = stm.executeQuery();
                //5.Process
                while (rs.next()) {
                    //5.1 get data from rs
                    String username = rs.getString("username"); //lay tu cau lenh SQL o tren
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data into DTO
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    //5.3 add DTO into List
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//end accounts had NOT existed
                    this.accounts.add(dto);
                }
            }//end username and password are existed
        }//end connection is available
        finally {
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
    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1.Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "DELETE FROM Registration "
                        + "WHERE username = ?";
                //3.Create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4.Execute Querry
                int effectiveRows = stm.executeUpdate();
                //5.Process
                if (effectiveRows > 0) {
                    result = true;
                }
            }//end connection is available
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

    public boolean updateAccount(String username, String password, boolean isAdmin)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "UPDATE Registration "
                        + "SET password = ?, "
                        + "isAdmin = ? "
                        + "WHERE username = ?";
                //3. Create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                //4. Execute Query
                int effectiveRows = stm.executeUpdate();
                //5. Process Result
                if (effectiveRows > 0) {
                    result = true;
                }
            }//end connection is available
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
