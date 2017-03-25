package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EmailSender;
import model.OtpGenerator;
import model.beans.LoginBean;
import model.beans.UserBean;
import model.dao.CheckUsernameDAO;
import model.dao.PasswordDAO;
import model.dao.UserDAO;
import model.emailType;

public class ForgotPasswordServlet extends HttpServlet {

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
        if (request.getParameter("action").equals("forgotPassword")) {
            String username = request.getParameter("username");
            System.out.println("Forgot password username: " + username);
            CheckUsernameDAO cudao = new CheckUsernameDAO();
            //LoginBean user = new LoginBean();
            //user.setUserName(username);
            //ResetPasswordDAO r = new ResetPasswordDAO();
            PasswordDAO pdao = new PasswordDAO();
            UserDAO userdao = new UserDAO();
            UserBean user = new UserBean();
            user = userdao.getUser(user, username);
            //LoginBean bean = new LoginBean();
            //bean.setUserName(username);
            if (!cudao.checkUsername(user)) {
                if (user.isVerified()) {
                    int otp = OtpGenerator.generateOTP();
                    user.setVcode(otp);
                    pdao.setOTP(user);
                    EmailSender.sendEmail(user, emailType.FORGOT_PASSWORD);
                    response.getWriter().write("true");
                } else {
                    response.getWriter().write("not verified account");
                }
            } else {
                response.getWriter().write("not valid");
            }
        } else /*if (request.getParameter("action").equals("checkOTP"))*/ {
            int otp = Integer.parseInt(request.getParameter("otp"));
            String username = request.getParameter("username");
            System.out.println("usernaem " + username);
            PasswordDAO pdao = new PasswordDAO();
            UserBean user = new UserBean();
            user.setUsername(username);
            if (pdao.checkOTP(user, otp)) {
                //request.setAttribute("user", user);
                // System.out.println("Username "+request.getAttribute("username"));
                System.out.println("fowarding to resetpassword.jsp.....");
                //response.sendRedirect("resetpassword.jsp");
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
                System.out.println("Does it reach here?");
            } else {
                response.getWriter().write("false");
                System.out.println("or here?");
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
