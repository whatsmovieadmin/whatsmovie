package com.datastore;

import java.util.List;
import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class UserUtil {

	public static void addUser(User user) {
		if (user == null){
			return;
		}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {

			pm.makePersistent(user);
			
		} finally {
			pm.close();
		}
	}

	public static User getUserById(String email) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		User user=null;
		try {
			
			user= pm.getObjectById(User.class, email);
			
		} finally {
			pm.close();
		}
		
		return user;
	}
	
	public static User getUserByName(String name) {
		 
		PersistenceManager pm = PMF.get().getPersistenceManager();
		User user=null;
		try {
			
			List<User> listUsers=listObjectUsers();
			
			String email="";
			
			for(int i=0;i<listUsers.size();i++)
			{
				if((listUsers.get(i).getName().compareTo(name))==0)
				{
					email=listUsers.get(i).getEmail();
				}
			}
			
			user= pm.getObjectById(User.class, email);
			
		} finally {
			pm.close();
		}
		
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> listUsers(){
		 PersistenceManager pm = PMF.get().getPersistenceManager();
		 
		 List<String> userID=new Vector<String>();
		 Query query = pm.newQuery(User.class);
		 List<User> users=(List<User>) query.execute();
		 
		 try {
			 for (int i = 0; i < users.size(); i++) {
			     User u = users.get(i);
		
			     userID.add(u.getEmail());
			 }
		 } finally {
				pm.close();
		 } 
		 
		 return userID;
	}
	
	
	public static void updateUserPassword(String email, String password) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			User u=getUserById(email);
			if(u!=null)
			{
				u.setPassword(password);
				pm.makePersistent(u);
			}
		} finally {
			pm.close();
		}
	}
	
	public static void deleteUser(String email) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			User u=getUserById(email);
			if(u!=null)
			{
				pm.deletePersistent(pm.getObjectById(User.class, email));
			}
		} finally {
			pm.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> listNameUsers(){
		 PersistenceManager pm = PMF.get().getPersistenceManager();
		 
		 Query query = pm.newQuery("select name from "+User.class.getName()+"");
		 List<String> usersNames=(List<String>) query.execute();
		 
		 return usersNames;
	}
	
	@SuppressWarnings("unchecked")
	public static List<User> listObjectUsers(){
		 PersistenceManager pm = PMF.get().getPersistenceManager();
		 
		 Query query = pm.newQuery("select from "+User.class.getName()+"");
		 List<User> users=(List<User>) query.execute();
		 
		 return users;
		 
	}
	
	public static void updateUser(User u) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			if(u!=null)
			{
				pm.deletePersistent(pm.getObjectById(User.class, u.getEmail()));
				addUser(u);
			}
		} finally {
			pm.close();
		}
	}

}