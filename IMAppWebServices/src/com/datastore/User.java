package com.datastore;

import java.util.List;
import java.util.Vector;

import javax.jdo.annotations.*;

@PersistenceCapable (identityType=IdentityType.APPLICATION)
public class User {
	 
	 @PrimaryKey
	 @Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	 private String email;
	 
	 @Persistent 
	 private String name;
	 
	 @Persistent 
	 private String password;
	 
	 @Persistent 
	 private List<String> amigos;
	 
	 

	 public User(String account, String password, String name){
		  this.email=account;
		  this.name=name;
		  this.password=password;
		  this.amigos=new Vector<String>();
	 }
	 
	 public String getEmail() {
		return email;
	 }

	 public void setEmail(String email) {
		this.email = email;
	 }

	 public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	 }

	 public void setPassword(String password) {
		this.password = password;
	 }

	public List<String> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<String> amigos) {
		this.amigos = amigos;
	} 
}
