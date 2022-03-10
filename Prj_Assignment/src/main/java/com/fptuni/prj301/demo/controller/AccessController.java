/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.controller;

import com.fptuni.prj301.demo.dbmanager.AccessManager;
import com.fptuni.prj301.demo.model.User;
import com.fptuni.prj301.demo.model.UserSession;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AccessController_1", urlPatterns = {"/AccessController_1"})
public class AccessController extends HttpServlet {

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
        String path = request.getPathInfo();
            System.out.println(path);
            if (path.equals("/login")){
                
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                if (username == null){
                    RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);                
                }
                else{
                    AccessManager manager = new AccessManager();
                    User us = manager.login(username, password);

                    HttpSession ss = request.getSession(true);
                    ss.setAttribute("usersession", us);

                    if (us != null){
                        response.sendRedirect(request.getContextPath()+"/Product/adminproduct");
                    }else{
                        request.setAttribute("login-msg", "Wrong username or password");
                        RequestDispatcher rd = request.getRequestDispatcher("view/login.jsp");
                        rd.forward(request, response);
                    }
                }
                    
                
            }else if (path.equals("/logout")){
                HttpSession ss = request.getSession();
                ss.setAttribute("usersession", null);
                
                request.setAttribute("login-msg", "");
                RequestDispatcher rd = request.getRequestDispatcher("/view/login.jsp");
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
