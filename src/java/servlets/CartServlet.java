/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;
import domain.*;

import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext sc = getServletContext();
        
        HttpSession session = request.getSession();
        
        String option = request.getParameter("option");
        
        String url = "/index.html";
        
        if (option == null) {
            option = "shop";  // default action
        }
        
        if (option.equals("add")) {
            Cart cart = (Cart) session.getAttribute("cart");
            String productCode = request.getParameter("productCode");
            Product product = Product.find(productCode);            
            
            LineItem item = new LineItem();
            item.setProduct(product);
            item.setQuantity(1);
            cart.addItem(item);
            session.setAttribute("cart", cart);
            
            url = "/cart.jsp";
        }
        
        else if (option.equals("checkout")) {
            url = "/checkout.jsp";
        }
        
        else if (option.equals("init")) {
            Product.init();
            ArrayList<Product> products = Product.getProducts();
            Cart cart = new Cart();
            session.setAttribute("products", products);
            session.setAttribute("cart", cart);
            url = "/shop.jsp";
        }
        else if (option.equals("shop")) {
            url = "/shop.jsp";
        }
        
        else if (option.equals("remove")){
            Cart cart = (Cart) session.getAttribute("cart");
            String productCode = request.getParameter("productCode");
            cart.removeItem(productCode);
            session.setAttribute("cart", cart);
            url = "/cart.jsp";
        }
        
        else if (option.equals("update")){
            int quantity;
            Cart cart = (Cart) session.getAttribute("cart");
            
            String productCode = request.getParameter("productCode");
            String quantityString = request.getParameter("quantity");
            
            try {
                quantity = Integer.parseInt(quantityString);
            }
            
            catch (NumberFormatException e) {
                quantity = 1;
            }
            
            cart.updateItem(productCode, quantity);
            session.setAttribute("cart", cart);
            
            url = "/cart.jsp";
        }

        sc.getRequestDispatcher(url)
                .forward(request, response);
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
