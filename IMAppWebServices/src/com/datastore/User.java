package com.datastore;

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

	 public User(String account, String password, String name){
		  this.email=account;
		  this.name=name;
		  this.password=password;
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
}
