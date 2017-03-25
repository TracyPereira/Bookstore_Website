package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.UserBean;
import model.dao.EventDAO;

public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //response.getWriter().write("Hello");
        UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");

        System.out.println("homeservlet: " + user.getUsername());
        //add some values                  
        EventDAO eventDAO = new EventDAO();
        List eventList = eventDAO.getEventsByUserId(user.getUserID());
        System.out.println("userId = " + user.getUserID());
        //List eventList = eventDAO.getEventsByCategory("music");
        System.out.println(eventList);
        request.getSession().setAttribute("eventList", eventList);
        //request.getRequestDispatcher("hometest.jsp").forward(request, response);
        response.sendRedirect("hometest.jsp");
    } //what the hell am I doing :( 

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
