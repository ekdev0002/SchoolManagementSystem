package com.app.sms.utils;


import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Users {
	
    private ArrayList<User> users;

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    
    @XmlElement(name = "user")
    public ArrayList<User> getUsers() {
        return users;
    }
}