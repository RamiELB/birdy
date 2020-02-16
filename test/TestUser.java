package test;
import java.sql.SQLException;

import org.json.JSONException;

import services.User;
public class TestUser {
	public static void main(String [] args) throws JSONException, SQLException {
		User.createUser("momolegros", "pluspourlgt");
	}
}
