/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Business.Customer;
import Business.CustomerBL;
import DataBean.CustomerDL;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author phoeb
 */
@WebServlet(name = "CustomerProc", urlPatterns = {"/CustomerProc"})
public class CustomerProc extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String firstname, lastname, phone, email, street, province, postalCode;
            int houseNum;
            Customer s = new Customer();
            firstname = request.getParameter("firstname");
            lastname = request.getParameter("lastname");
            phone = request.getParameter("phone");
            email = request.getParameter("email");
            street = request.getParameter("street");
            province = request.getParameter("province");
            postalCode = request.getParameter("postalCode");
            String strhouseNo = request.getParameter("houseNum");
            s.setfName(firstname);
            s.setlName(lastname);
            s.setPhone(phone);
            s.setEmail(email);
            s.setStreet(street);
            s.setProvince(province);
            s.setPostalCode(postalCode);
            try {
                if (!strhouseNo.isEmpty()) {

                    houseNum = Integer.parseInt(strhouseNo);
                    s.setHouseNo(houseNum);

                }
                int customerId = CustomerBL.addCustomer(s);
                HttpSession session = request.getSession();

                if (customerId > 0) {
                    session.setAttribute("customerId", customerId);//check if need this?
                    response.sendRedirect("Confirm.jsp?customerId=" + customerId);
                } else {
                    response.sendRedirect("CustomerIndex.jsp?msg=Please try again");
                }
            } catch (Exception ex) {
                response.sendRedirect("CustomerIndex.jsp?msg=Please enter a valid house number");
            }
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
