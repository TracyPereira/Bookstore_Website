
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import model.ConnectionManager;
import model.beans.UserBean;

public class ConfirmUserDAO {
    
    Connection currentCon;
    ResultSet resultSet;
    public boolean confirmUser (String username, int vCode) {
        PreparedStatement stmt = null;
        
        String query;
        query = "select * from USERS where USERNAME = ? AND VCODE = ?";
                
        String confirmQuery = "update USERS set VERIFIED = 1 where USERNAME = ?";
        String resetVcode = "update USERS set VCODE = 0 where USERNAME = ?";
        try {
            //connect to DB
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setInt(2, vCode);
            resultSet = stmt.executeQuery();       
            System.out.println("yo");
            if (resultSet.next()){
               PreparedStatement st1 = currentCon.prepareStatement(confirmQuery);
               PreparedStatement st2 = currentCon.prepareStatement(resetVcode);
               st1.setString(1, username);
               st2.setString(1, username);
               st1.executeUpdate();
               st2.executeUpdate();
               //user.setVcode(0);
               //user.setVerified(1);
               
            } else {
                System.out.println("Something went wrong");
            }
            
            
        } catch (Exception ex) {
            System.out.println("2failed: An Exception has occurred! " + ex);
        } //some exception handling
        finally {
            if (resultSet != null) { try { resultSet.close();} catch (Exception e) {} resultSet = null;}
            if (stmt != null) { try { stmt.close();} catch (Exception e) {} stmt = null;}
            if (currentCon != null) { try { currentCon.close();} catch (Exception e) {} currentCon = null;}
        }   
        return true;
        
    }   
}
class testNew {
  
        
    public static void main(String []args) {
        ConfirmUserDAO c = new ConfirmUserDAO();
        c.confirmUser("thapa",4263);
 
    }
}
