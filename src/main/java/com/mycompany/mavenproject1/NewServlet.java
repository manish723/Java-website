/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

/**
 *
 * @author manish
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
//AS A REDIRECT ACTION IN THE FORM
public class NewServlet extends HttpServlet 
{

    Connection con = null;   
   
    ResultSet rset = null;

    public void init() {    
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
          
        } catch (SQLException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");  
        String pass = request.getParameter("password");
        Integer flag = 0;
        try {
            Statement st=con.createStatement();
            rset = st.executeQuery("select * from cust");
        } catch (SQLException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rset.next()) {
                if ((email.equalsIgnoreCase(rset.getString("email"))) && (pass.equalsIgnoreCase(rset.getString("password")))) {
                    flag = 1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (flag == 1) {
        response.getWriter().print("Login Successful");
        } else {
        response.getWriter().print("Please try again");
        }
    }
}


