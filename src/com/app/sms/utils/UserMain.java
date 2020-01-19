package com.app.sms.utils;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

public class UserMain {

	public static void main(String[] args) {

		// create Users
		Users inputUsers = new Users ();
		ArrayList<User> listInputUsers = new ArrayList<>();
		
		User user_1 = new User ();
		user_1.setLogin("ghislain");
		user_1.setPassword("oQi!qUw9");
		listInputUsers.add(user_1);
		
		User user_2 = new User ();
		user_2.setLogin("seydina");
		user_2.setPassword("r4@K0xmH");
		listInputUsers.add(user_2);
		
		inputUsers.setUsers(listInputUsers);
		
		try {
			Utilitaire.JAXB.init(Users.class);
			Utilitaire.JAXB.marshal(inputUsers, "users.xml");
			
			Users outputUsers = Utilitaire.JAXB.unmarshal("users.xml");
			ArrayList<User> listOutputUsers = outputUsers.getUsers();
						
			for ( User user : listOutputUsers ) {
				System.out.println( user.getLogin() + ":" + user.getPassword() );
			}
			
			listInputUsers.remove(user_2);
			Utilitaire.JAXB.marshal(inputUsers, "users.xml");
			
		} catch (JAXBException | FileNotFoundException e) {
			System.err.println("Error occured : " + e.getMessage());
		}
	}
}
