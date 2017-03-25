
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.ConnectionManager;
import model.beans.EventBean;
import model.beans.UserBean;

public class EventDAO {
    private static Connection currentCon = null;
    private static ResultSet resultSet = null;
    
    public EventBean getSingleEvent(int eventId) {
        PreparedStatement stmt = null;
        EventBean bean = new EventBean();
        String query = "select * from EVENTS where EVENT_ID = ?";
        try {
            //connect to DB 
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.prepareStatement(query);
            stmt.setInt(1 ,eventId);
            resultSet = stmt.executeQuery();       
         
            if (resultSet.next()) {
                bean.poplulateData(resultSet);
            } 
            else  {
                System.out.println("Event does not exist");
            }
        } catch (Exception ex) {
            System.out.println("Single Event failed: An Exception has occurred! " + ex);
        } //some exception handling
        finally {
            if (resultSet != null) { try { resultSet.close();} catch (Exception e) {} resultSet = null;}
            if (stmt != null) { try { stmt.close();} catch (Exception e) {} stmt = null;}
            if (currentCon != null) { try { currentCon.close();} catch (Exception e) {} currentCon = null;}
        }   
        return bean;
    }
    public List getEventsByCategory (String category) {
        PreparedStatement stmt = null;
        List eventList = new ArrayList();
        String query = "select * from EVENTS where EVENT_TYPE = ?";
        try {
            //connect to DB 
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.prepareStatement(query);
            stmt.setString(1, category);
            resultSet = stmt.executeQuery();       
            System.out.println(stmt);
            while (resultSet.next()) {
                EventBean bean = new EventBean();
                bean.poplulateData(resultSet);
                eventList.add(bean);
            } 
            
        } catch (Exception ex) {
            System.out.println("getEventsByCategory() Event failed: An Exception has occurred! " + ex);
        } //some exception handling
        finally {
            if (resultSet != null) { try { resultSet.close();} catch (Exception e) {} resultSet = null;}
            if (stmt != null) { try { stmt.close();} catch (Exception e) {} stmt = null;}
            if (currentCon != null) { try { currentCon.close();} catch (Exception e) {} currentCon = null;}
        }   
        return eventList;
        
    }
    public List getEventsByPreferences(List preferences) { //WAIT
        Statement stmt = null;
        List eventList = new ArrayList();
        Iterator iterator = preferences.listIterator();
        String pref = "";
        while(iterator.hasNext()) {
            pref = pref.concat(("'" +(String)iterator.next()) + "', ");
        }
        pref = replaceLast(pref, ",", " ");
        System.out.println(pref);
        String query = "select * from EVENTS where EVENT_TYPE IN (" + pref + ")";
        System.out.println(query);
        try {
            //connect to DB 
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            resultSet = stmt.executeQuery(query);       
         
            while (resultSet.next()) {
                EventBean bean = new EventBean();
                bean.poplulateData(resultSet);
                eventList.add(bean);
            } 
            
        } catch (Exception ex) {
            System.out.println("getEventsByCategory() Event failed: An Exception has occurred! " + ex);
        } //some exception handling
        finally {
            if (resultSet != null) { try { resultSet.close();} catch (Exception e) {} resultSet = null;}
            if (stmt != null) { try { stmt.close();} catch (Exception e) {} stmt = null;}
            if (currentCon != null) { try { currentCon.close();} catch (Exception e) {} currentCon = null;}
        }   
        return eventList;
    }
    public List getEventsByUserId(int userId) {
        PreparedStatement stmt = null;
        List eventList = new ArrayList();
        
        String query = "SELECT * FROM EVENTS WHERE "
                + "EVENT_TYPE IN "
                + "(SELECT PREFERENCE FROM PREFERENCES WHERE PREFERENCES.USER_ID = ?)";
                
        try {
            //connect to DB 
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.prepareStatement(query);
            stmt.setInt(1, userId);
            resultSet = stmt.executeQuery();       
            System.out.println(stmt);
            while (resultSet.next()) {
                EventBean bean = new EventBean();
                bean.poplulateData(resultSet);
                eventList.add(bean);
            } 
            
        } catch (Exception ex) {
            System.out.println("getEventsByCategory() Event failed: An Exception has occurred! " + ex);
        } //some exception handling
        finally {
            if (resultSet != null) { try { resultSet.close();} catch (Exception e) {} resultSet = null;}
            if (stmt != null) { try { stmt.close();} catch (Exception e) {} stmt = null;}
            if (currentCon != null) { try { currentCon.close();} catch (Exception e) {} currentCon = null;}
        }   
        return eventList;
        
    }
    public List getEventsByUser(UserBean user) {
        return getEventsByUserId(user.getUserID());
    }
    public List getAllEventsWithLocation() {
        return null;
        //FOR GOOGLE MAPS
        //HAHAHAHAHAHAHAHAHAHAHA
    }
    private String replaceLast(String string, String substring, String replacement)
    {
        int index = string.lastIndexOf(substring);
        if (index == -1)
            return string;
        return string.substring(0, index) + replacement
          + string.substring(index+substring.length());
    }
    public static void main(String args[]) {
        List l = new ArrayList();
        l.add("music");
        l.add("sports");
        l.add("food");
        EventDAO a = new EventDAO();
        List eventsByPreferences = a.getEventsByCategory("music");
    }
}
