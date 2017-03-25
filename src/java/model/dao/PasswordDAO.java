package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.ConnectionManager;
import model.beans.UserBean;

public class PasswordDAO {
    PreparedStatement stmt = null;
    Connection currentCon;
    String query;
    ResultSet rs;
    
    public void resetPassword(UserBean user, String newPassword){
        
        query = "update users set PASSWORD = ? where USERNAME = ?";
        String resetQuery = "update users set VCODE =  0  where USERNAME = ?;";
        System.out.println(query);
        try {
            //connect to DB
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.prepareStatement(query);
            stmt.setString(1, newPassword);
            stmt.setString(2, user.getUsername());
            int i = stmt.executeUpdate();
            System.out.println("updated");
            stmt = currentCon.prepareStatement(resetQuery);
            stmt.setString(1, user.getUsername());
            stmt .executeUpdate();
            System.out.println("updated vcode" + i);
        } catch (Exception ex) {
            System.out.println("1failed: An Exception has occurred! " + ex);
        } //some exception handling
        finally {
            //if (resultSet != null) { try { resultSet.close();} catch (Exception e) {} resultSet = null;}
            if (stmt != null) { try { stmt.close();} catch (Exception e) {} stmt = null;}
            if (currentCon != null) { try { currentCon.close();} catch (Exception e) {} currentCon = null;}
        }
    }
    public void setOTP(UserBean user){
        query = "update users set vcode = "
                + user.getVcode() + " where USER_ID = "
                + user.getUserID() + " ;";
        try {
            //connect to DB
            currentCon = ConnectionManager.getConnection();
            int i=currentCon.prepareStatement(query).executeUpdate();
            System.out.println("updated" + i);

        } catch (Exception ex) {
            System.out.println("1failed: An Exception has occurred! " + ex);
        } //some exception handling
        finally {
            //if (resultSet != null) { try { resultSet.close();} catch (Exception e) {} resultSet = null;}
            if (stmt != null) { try { stmt.close();} catch (Exception e) {} stmt = null;}
            if (currentCon != null) { try { currentCon.close();} catch (Exception e) {} currentCon = null;}
        }
    }
    public boolean checkOTP(UserBean user, int otp) {
        query = "select * from users where vcode = "
                + otp + " and USERNAME = '"
                + user.getUsername() + "';";
        System.out.println(query);
        try {
            //connect to DB
            currentCon = ConnectionManager.getConnection();
            rs=currentCon.prepareStatement(query).executeQuery();
            rs.next();
            if (rs.getInt("VCODE") == otp) {
                System.out.println("checkOTP returns true");
                return true;
            }
        } catch (Exception ex) {
            System.out.println("3failed: An Exception has occurred! " + ex);
        } //some exception handling
        finally {
            if (rs != null) { try { rs.close();} catch (Exception e) {} rs = null;}
            if (stmt != null) { try { stmt.close();} catch (Exception e) {} stmt = null;}
            if (currentCon != null) { try { currentCon.close();} catch (Exception e) {} currentCon = null;}
        }
        return false;
    }
}
