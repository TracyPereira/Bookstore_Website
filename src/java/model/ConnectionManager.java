package model;

import java.sql.*;
import static model.Urls.DB_URL;

public class ConnectionManager {

    static Connection connection; //static?
    static String url = DB_URL;

    public static Connection getConnection() {

        try {
            
            // assuming "DataSource" is your DataSource name

            Class.forName("com.mysql.jdbc.Driver");

            try {
                connection = DriverManager.getConnection(url, "root", "root");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        return connection;
    }

    public static ResultSet fireSelect(String query,String exMessage) {
        Statement stmt = null;
        ResultSet resultSet = null;
           //connect to DB 
            Connection currentCon = getConnection();
        try {
            stmt = currentCon.createStatement();
            resultSet = stmt.executeQuery(query);       
         
        } catch (Exception ex) {
            System.out.println(exMessage + ex);
        } //some exception handling
        finally {
            //if (resultSet != null) { try { resultSet.close();} catch (Exception e) {} resultSet = null;}
            if (stmt != null) { try { stmt.close();} catch (Exception e) {} stmt = null;}
            if (currentCon != null) { try { currentCon.close();} catch (Exception e) {} currentCon = null;}
        }   
        return resultSet;
    }
    public static ResultSet fireSelect(String query){
        return fireSelect(query, "fire Select failed");
    }
    public static void main(String args[]) {
        Connection con = getConnection();
    }
}
