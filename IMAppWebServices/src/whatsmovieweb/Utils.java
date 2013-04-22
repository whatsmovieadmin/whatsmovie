package whatsmovieweb;

import java.util.List;

import com.datastore.User;
import com.datastore.UserUtil;

public class Utils {
	public static boolean existsUserWithThisEmail(String email)
    {
		List<String> u=UserUtil.listUsers();
		
		return (u.contains(email));
    }
	
	public static boolean existsUserWithThisName(String name)
    {
		List<String> u=UserUtil.listNameUsers();
		
		return (u.contains(name));
    }
	
	public static boolean areFriends(User u, String nameFriend)
	{			
		return (u.getAmigos().contains(nameFriend));
	}
}
