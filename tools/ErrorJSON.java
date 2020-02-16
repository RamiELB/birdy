package tools;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJSON {
	public static JSONObject serviceRefused(String message, int codeErreur) throws JSONException {
		JSONObject msg = new JSONObject();
		msg.put("err_msg", message);
		msg.put("err_code", codeErreur);
		return msg;
	}
	public static JSONObject serviceAccepted() throws JSONException {
		JSONObject msg = new JSONObject();
		return msg;
	}
}
