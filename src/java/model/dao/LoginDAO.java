package model.dao;


//import java.util.*;
import java.sql.*;
import model.ConnectionManager;
//import model.beans.LoginBean;
import model.beans.UserBean;
//import model.ConnectionManager;
//import model.LoginBean;

public class LoginDAO { 

    private static Connection currentCon = null;
    private static ResultSet resultSet = null;

    public UserBean login(UserBean bean) { //this was static eariler, changed it.

        //preparing some objects for connection 
        PreparedStatement stmt = null;

        String username = bean.getUsername();
        String password = bean.getPassword();

        String searchQuery //create an interface of queries and store all the queries there. :) :)
                = "select * from USERS where USERNAME = ? AND PASSWORD = ?";

        // "System.out.println" prints in the console; Normally used to trace the process
        System.out.println("Your user name is " + username);
        System.out.println("Your password is " + password);
        System.out.println("Query: " + searchQuery);

        try {
            //connect to DB 
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.prepareStatement(searchQuery);
            stmt.setString(1, username);
            stmt.setString(2, password);
            resultSet = stmt.executeQuery();
            boolean more = resultSet.next(); //boolean valid

            // if user does not exist set the isValid variable to false
            if (!more) {
                System.out.println("Sorry, you are not a registered user! Please sign up first" + more);
                bean.setValid(false);
            } //if user exists set the isValid variable to true
            else  {
                System.out.println("HERE");
                //String firstName = resultSet.getString("USERNAME");
                //String lastName = resultSet.getString("LastName");
                bean.populateData(resultSet);
                System.out.println("Welcome " + bean.getFirstname());
                //bean.setFirstname(firstName);
                //bean.setLastName(lastName);
                bean.setValid(true);
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

        return bean;

    }
}
