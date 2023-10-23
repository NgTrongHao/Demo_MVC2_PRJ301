/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import cart.CartObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import order.OrderDAO;
import orderDetail.OrderDetailDAO;
import product.ProductDTO;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String BOOK_STORE = "bookStore.jsp";

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
        String url = BOOK_STORE;
        try {
            //1. Customer goes to cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. Customer get his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
//                    Map<String, Integer> items = cart.getItems();
//                    items.clear();
                    //3. Customer gets all item
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        List<ProductDTO> products = (List<ProductDTO>) session.getAttribute("STORE");
                        if (products != null) {
                            OrderDAO orderDAO = new OrderDAO();
                            String orderID = orderDAO.createOrderID();
                            boolean createOrderResult = orderDAO.insertOrder(orderID);
                            System.out.println(createOrderResult);
                            if (createOrderResult) {
                                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                                int count = 0;
                                for (String key : items.keySet()) {
                                    ++count;
                                    for (ProductDTO product : products) {
                                        String productID = product.getProductID();
                                        System.out.println(key + " : " + productID);
                                        if (key.equals(product.getProductName())) {
                                            System.out.println(productID + items.get(key) + orderID);
                                            boolean insertOrderDetail
                                                    = orderDetailDAO.insertOrderDetail(count, productID, items.get(key), orderID);
//                                            if (insertOrderDetail) {
//                                                items.remove(key);
//                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                session.setAttribute("CART", cart);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
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
