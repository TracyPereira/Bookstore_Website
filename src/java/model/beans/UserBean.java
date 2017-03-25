package model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class UserBean {

    private int userID;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Date DOB;
    private String email;
    private String sex;
    private List perferences;
    private boolean isValid;
    private String phone;
    private int vcode;
    private int verified;

    public int getVcode() {
        return vcode;
    }

    public void setVcode(int vcode) {
        this.vcode = vcode;
    }
    public boolean isVerified() {
        if(verified == 1)
            return true;
        else
            return false;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List getPerferences() {
        return perferences;
    }

    public void setPerferences(List perferences) {
        this.perferences = perferences;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public void populateData(ResultSet res) throws SQLException {
        if (res.getInt("USER_ID") != 0) {
            this.setUserID(res.getInt("USER_ID"));
        }
        if (res.getString("FIRST_NAME") != null) {
            this.setFirstname(res.getString("FIRST_NAME"));
        }
        if (res.getString("LAST_NAME") != null) {
            this.setLastname(res.getString("LAST_NAME"));
        }
        if (res.getString("USERNAME") != null) {
            this.setUsername(res.getString("USERNAME"));
        }
        if (res.getString("PASSWORD") != null) {
            this.setPassword(res.getString("PASSWORD"));
        }
        if (res.getString("EMAIL") != null) {
            this.setEmail(res.getString("EMAIL"));
        }
        if (res.getString("SEX") != null) {
            this.setSex(res.getString("SEX"));
        }
        if (res.getDate("DOB") != null) {
            this.setDOB(res.getDate("DOB"));
        }
        /*if(res.getInt("VERIFIED") != null)*/ 
            this.setVerified(res.getInt("VERIFIED"));
        /*if(res.getInt("VCODE") != null)*/ 
            this.setVcode(res.getInt("VCODE"));
        

    }

}
