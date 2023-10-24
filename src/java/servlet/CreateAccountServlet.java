/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import registration.RegistrationCreateError;
import registration.RegistrationDAO;
import registration.RegistrationDTO;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    private final String USER_LENGTH_ERROR = "Username is required tuping form 6 to 30 characters";
    private final String PASSWORD_LENGTH_ERROR = "Password is required tuping form 6 to 30 characters";
    private final String CONFIRM_ERRORS = "Confirm must match Password";
    private final String FULLNAME_LENGTH_ERROR = "Fullname is required tuping form 6 to 30 characters";
    private final String CREATE_ERROR_PAGE = "createAccount.jsp";
    private final String LOGIN_PAGE = "login.html";
    private final String USER_EXISTED_ERROR = "Username is existed";

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
        //1. get all parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        String url = CREATE_ERROR_PAGE;

        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr = false;
        try {
            //2. verify all user's errors
            if (username.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameLengthErr(USER_LENGTH_ERROR);
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLengthErr(USER_LENGTH_ERROR);
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmNotMatch(CONFIRM_ERRORS);
            }
            if (fullName.trim().length() < 2 || username.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLengthErr(FULLNAME_LENGTH_ERROR);
            }
            if (foundErr) {  //errors occur
                //cache to attribute then forward page to display
                request.setAttribute("CREATE_ERRORS", errors);
            } else {//no error
                //3. call DAO
                //3.1 new DAO
                RegistrationDAO dao = new RegistrationDAO();
                //3.2 call method of DAO
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createNewAcount(dto);
                //4. process result
                if (result) {
                    url = LOGIN_PAGE;
                }//insert action successfully
            }//end no error
        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("CreateAccountServlet _ SQL: " + errMsg);
            if(errMsg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + USER_EXISTED_ERROR);
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } catch (NamingException ex) {
            log("CreateAccountServlet _ Naming: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("CreateAccountServlet _ ClassNotFound: " + ex.getMessage());
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
