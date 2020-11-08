/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Business.Crust;
import Business.Pizza;
import Business.PizzaBL;
import Business.Size;
import Business.Toppings;
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
@WebServlet(name = "PizzaProc", urlPatterns = {"/PizzaProc"})
public class PizzaProc extends HttpServlet {

    ArrayList<Pizza> pizzas = new ArrayList<>();

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
            HttpSession session = request.getSession();
//            Size s = PizzaDL.getSize(Integer.parseInt(request.getParameter("size")));
//            session.setAttribute("size", s );
//            Crust c=PizzaDL.getCrust(Integer.parseInt(request.getParameter("crust")));
//            session.setAttribute("crust", c);
//            ArrayList<Toppings> t=PizzaDL.getSelectToppings(request.getParameterValues("topping"));
//            session.setAttribute("toppings", t);
            try {
                int size = Integer.parseInt(request.getParameter("size"));
                session.setAttribute("size", size);
                int crust = Integer.parseInt(request.getParameter("crust"));
                session.setAttribute("crust", crust);

                String[] toppings = request.getParameterValues("topping");
                session.setAttribute("toppings", toppings);

                Pizza p = new Pizza();
                p.setSizeId(Integer.parseInt(session.getAttribute("size").toString()));
                p.setCrustTypeId(Integer.parseInt(session.getAttribute("crust").toString()));
                double price = PizzaBL.calculatePizza(size, toppings);
                p.setPrice(price);
                p.setToppings(toppings);

                int noPizza = Integer.parseInt(request.getParameter("noPizza"));
                for (int i = 1; i <= noPizza; i++) {
                    pizzas.add(p);
                }
                session.setAttribute("pizzaArray", pizzas);

                if (session.getAttribute("size") == null || session.getAttribute("crust") == null) {
                    response.sendRedirect("index.jsp?msg=Please try again");
                } else {

                    response.sendRedirect("CustomerIndex.jsp");
                }
            } catch (Exception e) {
                response.sendRedirect("index.jsp?msg=Please enter number of pizza");
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
