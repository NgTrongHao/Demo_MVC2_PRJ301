/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class MyContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application is deployed.............");
        //1. get current context
        ServletContext context = sce.getServletContext();
        //2. get siteMaps file
        String siteMapsFile = context.getInitParameter("SITEMAPS_FILE_PATH");
        //3. load properties
        Properties siteMaps = null;
        InputStream is = null;
        try {
            is = context.getResourceAsStream(siteMapsFile);
            siteMaps = new Properties();
            siteMaps.load(is);
            //4. store siteMap to context Attribute
            context.setAttribute("SITEMAPS", siteMaps);
        } catch (IOException ex) {
            context.log("MyContextServletListener _ IO: " + ex.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    context.log("MyContextServletListener _ IO: " + ex.getMessage());
                }
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application is destroyed.");
    }
}
