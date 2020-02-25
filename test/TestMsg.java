package test;

import org.json.JSONException;

public class TestMsg {
	public static void main(String [] args) throws JSONException{
		//services.Messages.send_msg(3, "-1", "CECI EST UN TEST");
		//System.out.println(services.Messages.get_msg_user(3).toString());
		System.out.println(services.Messages.get_msg_user(3));
	}
}
