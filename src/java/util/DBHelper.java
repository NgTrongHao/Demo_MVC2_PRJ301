/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class DBHelper implements Serializable {

    public static Connection createConnection()
            throws ClassNotFoundException, SQLException, NamingException {
//        //1.Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2.Create connection String url
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJ301Database;instanceName=NGTRONGHAO";
//        //3.Open connection
//        Connection con = DriverManager.getConnection(url,"sa","20020509");
//        
//        return con;

        //1. get current context
        Context currentContext = new InitialContext();
        //2. look up tomcat context
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
        //3. look up datasource
        DataSource ds = (DataSource) tomcatContext.lookup("SE1708DS");
        //4. open connection form DS
        Connection con = ds.getConnection();

        return con;
    }
}
