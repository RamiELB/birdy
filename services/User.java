package services;

import org.json.JSONException;
import org.json.JSONObject;
import bd.Database;
import tools.UserTools;

import java.sql.*;

public class User {
	
	public static JSONObject login(String login, String mdp) throws JSONException, SQLException {
		if( (login == null) || (mdp == null)) {
			return tools.ErrorJSON.serviceRefused("Wrong Arguments", -1);
		}
		
		Connection c = Database.getMySQLConnection();
		
		if(!(tools.UserTools.UserLoginExists(login, c))){
			return tools.ErrorJSON.serviceRefused("User does not exist", -1);
		}
		
		/* A COMPLETER : INSERER LA CONNEXTION DANS LA BD */
		
		c.close();
		return tools.ErrorJSON.serviceAccepted();
	}
	
	public static JSONObject logout(String id) throws JSONException, SQLException {
		
		return tools.ErrorJSON.serviceAccepted();
	}
	
	public static JSONObject createUser(String login, String mdp, String pseudo) throws JSONException, SQLException {
		if( (login == null) || (mdp == null) || (pseudo == null) || (login.length() < 3) || (mdp.length() < 3) || (pseudo.length() < 3)) {
			return tools.ErrorJSON.serviceRefused("Wrong Arguments", -1);
		}
		
		Connection c = Database.getMySQLConnection();
		
		if((tools.UserTools.UserLoginExists(login, c))){
			return tools.ErrorJSON.serviceRefused("User exists already", -1);
		}	
		
		if(UserTools.insertUser(login, mdp, pseudo, c)) {
			return tools.ErrorJSON.serviceAccepted();
		}
		c.close();
		return tools.ErrorJSON.serviceRefused("DB Error", 1000);
	}
}
