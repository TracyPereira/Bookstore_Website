/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EmailSender;
import model.OtpGenerator;
import model.beans.UserBean;
import model.dao.RegisterDAO;
import model.emailType;

/**
 *
 * @author MY
 */
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("Register Servlet");
        System.out.println(request.getParameter("firstname") + " FN");
        System.out.println("Date " + request.getParameter("dob"));
        
        if(!isHuman(request)) {
            response.setHeader("isHuman", "false");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        
        UserBean user = new UserBean();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        user.setFirstname((String) request.getParameter("firstname"));
        user.setLastname((String) request.getParameter("lastname"));
        user.setUsername((String) request.getParameter("username"));
        user.setPassword((String) request.getParameter("password"));
        user.setDOB(new java.sql.Date(format.parse(request.getParameter("dob")).getTime()));
        user.setEmail((String) request.getParameter("email"));
        user.setPhone((String) request.getParameter("phone"));
        //SOLVE THE DATE PROBLEM!!!!!!!!!!!!

        String sex = request.getParameter("sex");
        if (sex.equals("male")) {
            user.setSex("M");
        } else {
            user.setSex("F");
        }

        String[] preferences = request.getParameterValues("preference");
        System.out.println("preferences" + preferences);
        ArrayList pref = new ArrayList();
        //pref.addAll(Arrays.asList(preferences));
        for (String p : preferences) {
            pref.add(p);
        }
        user.setPerferences(pref);

        user.setVcode(OtpGenerator.generateOTP());

        RegisterDAO registerer = new RegisterDAO();
        user = registerer.registerUser(user);

        EmailSender.sendEmail(user, emailType.REGISTER);
        //WAIT BCBCBCBCBCBCBC
        //HttpSession session = request.getSession();
        //session.setAttribute("currentSessionUser", user);

        RequestDispatcher view = request.getRequestDispatcher("checkmail.jsp");
        view.forward(request, response);
    }

    private boolean isHuman(HttpServletRequest request) {
        //System.out.println("PV = " + request.getParameterNames());
        System.out.println("realPerson = " + request.getParameter("realPerson"));
        if (rpHash(request.getParameter("realPerson")).equals(
                request.getParameter("realPersonHash"))) {
            System.out.println("accepted Captcha");
            return true;
        } else {
            System.out.println("Rejected Captcha");
            return false;
        }
    }

    private String rpHash(String value) {
        int hash = 5381;
        value = value.toUpperCase();
        for (int i = 0; i < value.length(); i++) {
            hash = ((hash << 5) + hash) + value.charAt(i);
        }
        return String.valueOf(hash);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
