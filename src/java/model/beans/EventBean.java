
package model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class EventBean {
    private int eventid;
    private String eventname;
    private Date dateCreated;
    private Date dateOfEvent;
    private String description;
    private int seatsAvail;
    // Blob image;
    private String imagePath;

    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(Date dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeatsAvail() {
        return seatsAvail;
    }

    public void setSeatsAvail(int seatsAvail) {
        this.seatsAvail = seatsAvail;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public void poplulateData(ResultSet resultSet) throws SQLException {
        this.setEventid(resultSet.getInt("EVENT_ID"));
        this.setEventname(resultSet.getString("EVENT_NAME"));
        this.setDescription(resultSet.getString("DESCRIPTION"));
        this.setDateCreated(resultSet.getDate("DATE_CREATED"));
        this.setDateOfEvent(resultSet.getDate("DATE_OF_EVENT"));
        this.setImagePath(resultSet.getString("IMAGE_PATH"));
        this.setSeatsAvail(resultSet.getInt("SEATS_AVAIL"));

    }
}
