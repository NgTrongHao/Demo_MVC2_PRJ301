/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import registration.RegistrationDAO;
import util.DBHelper;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class LoginServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
    private final String INVALID_PAGE = "invalid.html";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

//        String username = request.getParameter("userName");
//        String password = request.getParameter("password");
//        String button = request.getParameter("btAction");
//
//        try {
//            out.println("Username " + username + " - " + password + " - " + button);
//
//            System.out.println("Username " + username + " - " + password + " - " + button);
//
//            Connection con = DBHelper.createConnection();
//            
//            if(con != null){
//                out.println("Database is connected!");
//            } else {
//                out.println("Fail!");
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            ex.printStackTrace();
//        } 
//        finally {
//            out.close();
//        }
//        //1.get all parameters
//        String button = request.getParameter("btAction");
//        String url = INVALID_PAGE;
//
//        try {
//            if (button.equals("Login")) {
//                String username = request.getParameter("userName");
//                String password = request.getParameter("password");
//                //2. call DAO
//                //  2.1 new DAO object
//                RegistrationDAO dao = new RegistrationDAO();
//                //  2.2 call method of DAO
//                boolean result = dao.checkLogin(username, password);
//                //3. process result
//                if (result) {
//                    url = SEARCH_PAGE;
//                }
//                //end username and password are verified
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } finally {
////            response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//            out.close();
//        }

        //1.get all parameters
        String url = INVALID_PAGE;
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        try {

            //2. call DAO
            //  2.1 new DAO object
            RegistrationDAO dao = new RegistrationDAO();
            //  2.2 call method of DAO
            boolean result = dao.checkLogin(username, password);
            //3. process result
            if (result) {
                url = SEARCH_PAGE;
                Cookie cookie = new Cookie(username, password);
                cookie.setMaxAge(60 * 10);
                response.addCookie(cookie);
            }
            //end username and password are verified

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
