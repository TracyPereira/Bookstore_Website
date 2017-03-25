package test;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import model.beans.EventBean;
import model.dao.EventDAO;

public class Test {

    public static void main(String[] args) {
        List prefList = new ArrayList();
        prefList.add("music");
        EventDAO edao = new EventDAO();
        /*
        EventBean event1 = edao.getSingleEvent(100); //getSingleEvent()
        System.out.println(event1.getDateCreated());    //working
        System.out.println(event1.getDateOfEvent());
        System.out.println(event1.getDescription());
        System.out.println(event1.getEventid());
        System.out.println(event1.getSeatsAvail());
        System.out.println(event1.getEventname());
         */
        /*
        List eventList = edao.getEventsByCategory("music"); //getEventsByCategory()
        Iterator it = eventList.listIterator();             //working
        System.out.println(it.hasNext());
        while (it.hasNext()) {
            EventBean eb = (EventBean) it.next();
            System.out.println(eb.getDateCreated());
            System.out.println(eb.getDateOfEvent());
            System.out.println(eb.getDescription());
            System.out.println(eb.getEventid());
            System.out.println(eb.getEventname());
            System.out.println(eb.getSeatsAvail());
            System.out.println();
        }*/
        List eventList = edao.getEventsByUserId(1); //getEventsByUserId()
        Iterator it = eventList.listIterator();             //working
        System.out.println(it.hasNext());
        while (it.hasNext()) {
            EventBean eb = (EventBean) it.next();
            System.out.println(eb.getDateCreated());
            System.out.println(eb.getDateOfEvent());
            System.out.println(eb.getDescription());
            System.out.println(eb.getEventid());
            System.out.println(eb.getEventname());
            System.out.println(eb.getSeatsAvail());
            System.out.println();
        }
        
    }
}
