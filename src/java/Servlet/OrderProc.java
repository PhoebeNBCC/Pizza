/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Business.Order;
import Business.OrderBackingBean;
import Business.Pizza;
import Business.PizzaBL;
import DataBean.PizzaDL;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "OrderProc", urlPatterns = {"/OrderProc"})
public class OrderProc extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderProc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderProc </h1>");
            HttpSession session = request.getSession();

            String strTotal = session.getAttribute("total").toString();
            double total = Double.parseDouble(strTotal);

            String id = session.getAttribute("customerId").toString();
            int custId = Integer.parseInt(id);

            Order o = new Order();
            o.setTotalPrice(total);
            o.setCustomerId(custId);
            int orderId = OrderBackingBean.addOrder(o);

            if (orderId > 0) {
                session.setAttribute("orderId", orderId);

//                Pizza p = new Pizza();
//                p.setSizeId(Integer.parseInt(session.getAttribute("size").toString()));
//                p.setCrustTypeId(Integer.parseInt(session.getAttribute("crust").toString()));
//                p.setPrice(Double.parseDouble(session.getAttribute("price").toString()));
//                p.setOrderId(orderId);
                ArrayList<Pizza> pizzas = (ArrayList<Pizza>) session.getAttribute("pizzaArray");
                for (Pizza p : pizzas) {
                    int pizzaId = PizzaBL.InsertPizza(p);
                    if (pizzaId > 0) {
                        int count = PizzaBL.addPizzaToppingsMap(pizzaId, p.getToppings());
                    }
                }

                if (request.getParameter("payment").equals("COD")) {
                    response.sendRedirect("OrderInfo.jsp");
                } else {
                    response.sendRedirect("https://www.paypal.com/ca/signin");
                }

            } else {
                response.sendRedirect("Confirm.jsp?msg=Please try again");
            }
            out.println("</body>");
            out.println("</html>");
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
