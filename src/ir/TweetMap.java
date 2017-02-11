package ir;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import twitter4j.*;

public class TweetMap {
	
	/**
	 * @uml.property  name="map"
	 * @uml.associationEnd  qualifier="valueOf:java.lang.Long java.util.ArrayList"
	 */
	private HashMap<Long, ArrayList<Status>> map;
	private static ArrayList<User> users;
	
	
	public TweetMap() 
	{
		map = new HashMap<Long, ArrayList<Status>>();
		users = new ArrayList<User>();
	}
	
	
	public void add(User user, ArrayList<Status> statuses)
	{
		this.users.add(user);
		this.map.put(user.getId(), statuses);
	}


	public HashMap<Long, ArrayList<Status>> getMap() {
		return map;
	}


	public static ArrayList<User> getUserList() {
		return users;
	}
	
	public ArrayList<Status> getStatusListByUser(User user)
	{
		return map.get(user.getId());
	}
	
}
