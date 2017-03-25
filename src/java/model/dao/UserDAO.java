
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import model.ConnectionManager;
import model.beans.UserBean;

/**
 *
 * @author Dell
 */
public class UserDAO {
 private static Connection currentCon = null;
    private static ResultSet resultSet = null;

    public UserBean getUser(UserBean user ,String username) { //this was static eariler, changed it.

        //preparing some objects for connection 
        PreparedStatement stmt = null;

        //String username = user.getUsername();
        //String password = user.getPassword();

        String searchQuery //create an interface of queries and store all the queries there. :) :)
                = "select * from USERS where USERNAME = ?;";

        // "System.out.println" prints in the console; Normally used to trace the process
        //System.out.println("Your user name is " + username);
        //System.out.println("Your password is " + password);
        System.out.println("Query UserDAO: " + searchQuery);

        try {
            //connect to DB 
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.prepareStatement(searchQuery);
            stmt.setString(1, username);
            resultSet = stmt.executeQuery();
            boolean more = resultSet.next(); //boolean valid

            // if user does not exist set the isValid variable to false
            if(more)  {
                System.out.println("HERE");
                //String firstName = resultSet.getString("USERNAME");
                //String lastName = resultSet.getString("LastName");
                user.populateData(resultSet);
                //System.out.println("Welcome " + bean.getFirstname());
                //bean.setFirstname(firstName);
                //bean.setLastName(lastName);
                user.setValid(true);
            }
        } catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        } //some exception handling
        finally {
            if (resultSet != null) {
                try {
                  resultSet.close(); //connection.close(); ?????
                } catch (Exception e) {
                }
                resultSet = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }

        return user;
   
}
}
