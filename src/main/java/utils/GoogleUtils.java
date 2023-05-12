package utils;

import static common.Constants.GOOGLE_CLIENT_ID;
import static common.Constants.GOOGLE_CLIENT_SECRET;
import static common.Constants.GOOGLE_GRANT_TYPE;
import static common.Constants.GOOGLE_LINK_GET_TOKEN;
import static common.Constants.GOOGLE_LINK_GET_USER_INFO;
import static common.Constants.GOOGLE_REDIRECT_URI;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.GooglePoJo;



public class GoogleUtils {

	private GoogleUtils() {

	}

	public static String getToken(String code) throws ClientProtocolException,IOException  {
		String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
				.bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID)
						.add("client_secret", GOOGLE_CLIENT_SECRET)
						.add("redirect_uri", GOOGLE_REDIRECT_URI)
						.add("code", code).add("grant_type", GOOGLE_GRANT_TYPE)
						.build())
				.execute().returnContent().asString();
		 JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
	      String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
	      return accessToken;
	}
	public static GooglePoJo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
	    String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
	    String response = Request.Get(link).execute().returnContent().asString();
	    GooglePoJo googlePojo = new Gson().fromJson(response, GooglePoJo.class);
	    return googlePojo;
	  }


}
