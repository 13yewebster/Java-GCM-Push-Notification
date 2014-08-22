package com.bye.project;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bye.project.Content;

public class PostToGcm extends HttpServlet {


	public static void post(HttpServletRequest request, HttpServletResponse response, String apiKey, Content content){


		try{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 1. URL
		URL url = new URL("https://android.googleapis.com/gcm/send");

		// 2. Open connection
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// 3. Specify POST method
		conn.setRequestMethod("POST");

		// 4. Set the headers
		conn.setRequestProperty("Authorization", "key="+apiKey);
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Content-Type", "application/json;");

		conn.setUseCaches (false);
		conn.setDoInput(true);
		conn.setDoOutput(true);


			// 5. Add JSON data into POST request body

			//`5.1 Use Jackson object mapper to convert Content object into JSON
	    	ObjectMapper objectMapper = new ObjectMapper();

	    	// 5.2 Get connection output stream
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

			//Log Console
			//System.out.println("Output JSON : " + objectMapper.writeValueAsString(content));

			// 5.3 Copy Content "JSON" into
			objectMapper.writeValue(wr, content);

			// 5.4 Send the request
			wr.flush();

			// 5.5 close
			wr.close();

			// 6. Get the response
			int responseCode = conn.getResponseCode();
			//System.out.println("\nSending 'POST' request to URL : " + url);
			//System.out.println("Response Code : " + responseCode);

			out.println(responseCode);
			System.out.println(responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer getResponse = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				getResponse.append(inputLine);
			}
			in.close();

			// 7. Print result
			//System.out.println(response.toString());

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
}
