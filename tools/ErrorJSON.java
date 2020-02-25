package tools;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJSON {
	public static JSONObject serviceRefused(String message, int codeErreur){
		JSONObject msg = new JSONObject();
		try {
			msg.put("err_msg", message);
			msg.put("err_code", codeErreur);
		}catch(JSONException e) {
			e.printStackTrace();
		}
		return msg;
	}
	public static JSONObject serviceAccepted(){
		JSONObject msg = new JSONObject();
		return msg;
	}
}
