
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import model.ConnectionManager;
import model.beans.LoginBean;
import model.beans.UserBean;


public class CheckUsernameDAO {
    private static Connection currentCon = null;
    private static ResultSet resultSet = null;
    public boolean checkUsername(LoginBean bean) { //this was static eariler, changed it.

        //preparing some objects for connection 
        PreparedStatement stmt = null;

        String username = bean.getUsername();
        //String password = bean.getPassword();

        String searchQuery //create an interface of queries and store all the queries there. :) :)
                = "select * from USERS where USERNAME = ?"; 
        
        // "System.out.println" prints in the console; Normally used to trace the process
        System.out.println("Your user name is " + username);
        System.out.println("CheckUsernameDAO Query: " + searchQuery);

        try {
            //connect to DB 
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.prepareStatement(searchQuery);
            stmt.setString(1, username);
            resultSet = stmt.executeQuery();
            //resultSet = ConnectionManager.fireSelect(searchQuery, "check username query");
            System.out.print("resultSet: " + resultSet);
            if (resultSet.next())
                return false;
            else
                return true;

           
        } catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        } //some exception handling
       finally {
            if (resultSet != null) { try { resultSet.close();} catch (Exception e) {} resultSet = null;}
            if (stmt != null) { try { stmt.close();} catch (Exception e) {} stmt = null;}
            if (currentCon != null) { try { currentCon.close();} catch (Exception e) {} currentCon = null;}
        }   
       
        System.out.println("reached here. It shouldn't have.");
        return false;

    }
    public boolean checkUsername(UserBean user) {
        LoginBean lb = new LoginBean();
        lb.setUserName(user.getUsername());
        return checkUsername(lb);
    }
    
}
