package controller;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.UserBean;
import model.dao.EventDAO;
import model.dao.LoginDAO;
import static model.Urls.HOME_JSP;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        try {
            
            UserBean user = new UserBean();
            LoginDAO loginDAO = new LoginDAO();
            user.setUsername(request.getParameter("un"));
            user.setPassword(request.getParameter("pw"));
            
            user = loginDAO.login(user);
            
             
            if (user.isValid()) { 
                if (user.isVerified()) {
                response.setHeader("isValid", "true");
                System.out.println("NOW");
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", user); //this
                System.out.println("Login Servlet userId = "+ user.getUserID() + user.getLastname());
                //response.sendRedirect("home.jsp");
                //request.getRequestDispatcher("/HomeServlet").forward(request, response);
                response.sendRedirect("HomeServlet"); //logged-in page
                }
                else {
                    response.sendRedirect("checkmail.jsp");
                }
            } else {
                System.out.println(user.isValid());
                response.setHeader("isValid", "false");
                //response.
                //response.sendRedirect("index.jsp"); //error page 
                //response.sendRedirect("register.jsp"); //register page   
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Throwable theException) {
            System.out.println(theException);
        }
    }
}
