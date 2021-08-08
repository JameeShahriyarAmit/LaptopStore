/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StaffDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Staff;


@WebServlet(name = "StaffLoginServlet", urlPatterns = {"/StaffLogin"})
public class StaffLoginServlet extends HttpServlet {

        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // declaring a staffdao object
    StaffDao staffdao = new StaffDao();
    
    // declaring a staff object
    Staff staff ; 
        
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        
        
      
            staff = staffdao.checkLogin(userName, passWord);
        
        
        
        out.println(staff.getUsername());
        
        if(staff.getUsername() == null){
            out.println("<script>");
            out.println("    alert('Login/Password incorrect');");
            out.println("    window.location = '/LaptopStore/staffLogin.jsp'");
            out.println("</script>");
        }
        else {
            
            HttpSession session = request.getSession();
            session.setAttribute("username", userName);
            
            out.println("<script>");
            out.println("    alert('Login succesfull!');");
            out.println("    window.location = '/LaptopStore/list'");
            out.println("</script>");
            
            
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
