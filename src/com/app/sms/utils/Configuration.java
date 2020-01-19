package com.app.sms.utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;  

@XmlRootElement  // Spécifie l'élément racine du document Xml
public class Configuration {
	private String url = "jdbc:mysql://localhost/example";	
	private String user = "root";	
	private String password = "AtoSuQm!qUw6pWd";
	
	public Configuration() {}  

	@XmlElement // Spécifie un sous-élément pour l'élément racine.
	public String getUrl() {return url;}
	public void setUrl(String url) {this.url = url;}
	
	@XmlElement // Spécifie un sous-élément pour l'élément racine.
	public String getUser() {return user;}
	public void setUser(String user) {this.user = user;}
	
	@XmlElement // Spécifie un sous-élément pour l'élément racine.
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
}