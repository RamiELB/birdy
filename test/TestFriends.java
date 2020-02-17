package test;

import java.sql.SQLException;

import org.json.JSONException;

import services.Friends;

public class TestFriends {
	public static void main(String [] args) throws SQLException, JSONException {
		TestFriends.testAddFriend(1,2);
		TestFriends.testAddFriend(1,3);
		TestFriends.testAddFriend(2,1);
		TestFriends.testGetFriends(1);
		TestFriends.testDelFriend(1, 2);
		TestFriends.testGetFriends(1);
	}

	public static void testGetFriends(int id) throws SQLException, JSONException {
		System.out.println("Les amis de "+id+" sont : \n");
		System.out.println(Friends.get_friends(id).toString());
	}
	
	public static void testAddFriend(int id1, int id2) throws SQLException, JSONException {
		System.out.println(id1+" ajoute "+id2+" en ami\n");
		Friends.add_friend(id1, id2);
	}
	
	public static void testDelFriend(int id1, int id2) throws SQLException, JSONException {
		System.out.println(id1+" supprime "+id2+" de ses amis\n");
		Friends.delete_friend(id1, id2);
	}
}
