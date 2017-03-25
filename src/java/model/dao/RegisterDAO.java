package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import model.ConnectionManager;
import model.beans.EventBean;
import model.beans.UserBean;

public class RegisterDAO {
    Connection currentCon;
    ResultSet resultSet;
    public UserBean registerUser (UserBean user) {
        PreparedStatement stmt = null;
        
        String query;
        query = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, DOB, EMAIL, SEX, PHONE, VCODE) "
                + "VALUES(? ,?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            //connect to DB
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.prepareStatement(query);
            
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setDate(5, user.getDOB());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getSex());
            stmt.setString(8, user.getPhone());
            //set VERIFIED = 0 BUT defalult is akready 0
            stmt.setInt(9, user.getVcode());
            stmt.executeUpdate();       
            System.out.println("updated");
            resultSet= currentCon.prepareStatement("select * from USERS WHERE USERNAME = '" 
                    + user.getUsername() + "'")
                    .executeQuery();
            
            resultSet.next();
            int userId = resultSet.getInt("USER_ID");
            System.out.println("userId : " + userId);
            List pref = user.getPerferences();
            Iterator it = pref.listIterator();
            
            PreparedStatement state = null;  
            while(it.hasNext()) { 
                
                state = null;
                String myQuery = "INSERT INTO PREFERENCES (USER_ID, PREFERENCE) "
                        + "VALUES (?, ?)";
                state = currentCon.prepareStatement(myQuery);
                state.setInt(1, userId);
                state.setString(2, (String)it.next());
                state.executeUpdate();
            }
            user.setUserID(userId);
            user.setValid(true);
            
            
        } catch (Exception ex) {
            System.out.println("1failed: An Exception has occurred! " + ex);
        } //some exception handling
        finally {
            if (resultSet != null) { try { resultSet.close();} catch (Exception e) {} resultSet = null;}
            if (stmt != null) { try { stmt.close();} catch (Exception e) {} stmt = null;}
            if (currentCon != null) { try { currentCon.close();} catch (Exception e) {} currentCon = null;}
        }   
        return user;
        
    }
    
}
