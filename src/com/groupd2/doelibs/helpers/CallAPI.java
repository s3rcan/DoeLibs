package com.groupd2.doelibs.helpers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.os.AsyncTask;
/**
 * Use this class to make API request to the web server<br>
 * Usage:<br>
 * 	+Create a new instance<br>
 * 	+override onPostExecute<br>
 * 	+call execute<br>
 * <br>
 * Code sample:<br>
 * 		GET:
 * <pre>
 * {@code
 * 		CallAPI callAPI = new CallAPI(){
 *   		{@literal @}Override
 *   		protected void onPostExecute(String result) {
 *				JSONObject jObject = new JSONObject(result);
 *				token = jObject.getString("token");
 *				text.setText(token);	
 *    		}
 *    	};
 *    	callAPI.execute("http://www.itutbildning.nu:10000/api/Login?username=sebastian.gunnarsson@gmail.com&password=12345");
 * </pre>
 *    POST:
 * <pre>
 * {@code
 *    CallAPI callAPI = new CallAPI(){
 *    		{@literal @}Override
 *   		protected void onPostExecute(String result) {
 *    			text.setText(result);
 *			}
 *   	};
 *   	callAPI.execute("http://www.itutbildning.nu:10000/api/Test?token=" + token, "param1=Sercan", "param2=Sercan");
 *</pre>
 *
 *@author Sercan
 */
public class CallAPI extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {
		String urlString = params[0]; // URL to call
		String result = "";
		InputStream in = null;
		if (params.length == 1) {
			// HTTP Get
			try {
				URL url = new URL(urlString);
				HttpURLConnection urlConnection = (HttpURLConnection) url
						.openConnection();
				in = new BufferedInputStream(urlConnection.getInputStream());

				// json is UTF-8 by default
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in, "UTF-8"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				reader.close();
				result = sb.toString();
			} catch (Exception e) {
				// Oops
			} finally {
				try {
					if (in != null)
						in.close();
				} catch (Exception squish) {
				}
			}
		} else {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(urlString);

			//httppost.setHeader("Content-Type", "application/json;charset=utf-8");

			List<NameValuePair> postParams = new ArrayList<NameValuePair>();

			for (int i = 1; i < params.length; i++) {
				String[] str = params[i].split("=");
				if(str.length < 2)
					postParams.add(new BasicNameValuePair(str[0], null));
				else
					postParams.add(new BasicNameValuePair(str[0], str[1]));
			}
			try {
				httppost.setEntity(new UrlEncodedFormEntity(postParams, HTTP.UTF_8));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			InputStream inputStream = null;
			try {
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();

				inputStream = entity.getContent();
				// json is UTF-8 by default
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream, "UTF-8"), 8);
				StringBuilder sb = new StringBuilder();

				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				reader.close();
				result = sb.toString();
			} catch (Exception e) {
				// Oops
			} finally {
				try {
					if (inputStream != null)
						inputStream.close();
				} catch (Exception squish) {
				}
			}
		}
		return result;
	}
}
