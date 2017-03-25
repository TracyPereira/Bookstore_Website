
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.LoginBean;
import model.dao.CheckUsernameDAO;

public class CheckUsernameServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("application/json");
        //processRequest(request, response);
        LoginBean bean = new LoginBean();
        String username = request.getParameter("username");
        System.out.println(username);
        bean.setUserName(username);
        
        CheckUsernameDAO usernameDAO = new CheckUsernameDAO();
        
        if(usernameDAO.checkUsername(bean))
            response.getWriter().write("true");
        else
            response.getWriter().write("false");
    }

}
