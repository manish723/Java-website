/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manish
 */
@WebServlet(name = "registration", urlPatterns = {"/registration"})
public class registration extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Connection con = null;   
   

    public void init() {    
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
          
        } catch (SQLException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String name1 = request.getParameter("name1");
            String pass1 = request.getParameter("password1");
            String email1 = request.getParameter("email1");
            PreparedStatement ps = con.prepareStatement("INSERT INTO cust " + "VALUES (?,?,?)");
            ps.setString(1, email1);
            ps.setString(2, pass1);
            ps.setString(3, name1);
            ps.executeUpdate();
            response.sendRedirect("redirect.html");
        } catch (SQLException ex) {
            response.getWriter().print("Unsuccessful");
            Logger.getLogger(registration.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

}
