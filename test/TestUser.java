package test;
import java.sql.SQLException;
import java.sql.*;
import org.json.JSONException;
import services.User;
import tools.UserTools;
import bd.Database;

public class TestUser {
	public static void main(String [] args) throws JSONException, SQLException {
		test_create("philou", "lefifou", "ptdr");
		Connection c = Database.getMySQLConnection();
		if(test_exists_log("alakazam", c)) {
			System.out.println("Yes =)\n");
		}else {
			System.out.println("No =(\n");
		}
		c.close();
	}
	
	
	public static void test_create(String login, String mdp, String pseudo) throws JSONException, SQLException {
		User.createUser(login, mdp, pseudo);
	}
	
	public static boolean test_exists_log(String login, Connection c) throws SQLException {
		return UserTools.UserLoginExists(login, c);
	}
}

