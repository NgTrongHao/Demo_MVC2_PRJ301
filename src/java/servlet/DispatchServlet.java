/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.MyApplicationConstants;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

////    private final String LOGIN_PAGE = "login.html";
//    private final String LOGIN_PAGE = "";
////    private final String LOGIN_CONTROLLER = "search.html";
////    private final String LOGIN_CONTROLLER = "LoginServlet";
//    private final String LOGIN_CONTROLLER = "loginController";
////    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastNameServlet";
//    private final String SEARCH_LASTNAME_CONTROLLER = "searchController";
//    private final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController";
//    private final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";
//    private final String START_UP_CONTROLLER = "startUpController";
//    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
//    private final String LOGOUT_CONTROLLER = "LogOutServlet";
//    private final String VIEW_CART_PAGE = "viewCart.jsp";
//    private final String REMOVE_ITEMS_FROM_CART_CONTROLLER = "RemoveItemsFromCartServlet";
//    private final String CHECK_OUT_CART_CONTROLLER = "CheckOutServlet";
//    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";
//    private final String BOOK_STORE_CONTROLLER = "BookStoreServlet";
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
        //0. get current context
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //1. Which button did user click?
        String button = request.getParameter("btAction");
//        String url = LOGIN_PAGE;
        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
        try {
            if (button == null) { //welcome file trigger
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.START_UP_CONTROLLER);
            } else if (button.equals("Login")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
            } else if (button.equals("Search")) {
//                url = SEARCH_LASTNAME_CONTROLLER;
//                url = siteMaps.getProperty(SEARCH_LASTNAME_CONTROLLER);
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.SEARCH_LASTNAME_CONTROLLER);
            } else if (button.equals("delete")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.DELETE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Update")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.UPDATE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Add Book to Your Cart")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.ADD_ITEM_TO_CART_CONTROLLER);
            } else if (button.equals("LogOut")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGOUT_CONTROLLER);
            } else if (button.equals("View Your Cart")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.VIEW_CART_PAGE);
            } else if (button.equals("Remove Selected Items")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.REMOVE_ITEMS_FROM_CART_CONTROLLER);
            } else if (button.equals("Check-out")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.CHECK_OUT_CART_CONTROLLER);
            } else if (button.equals("Create New Account")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.CREATE_ACCOUNT_CONTROLLER);
            } else if (button.equals("bookStore")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.BOOK_STORE_CONTROLLER);
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
