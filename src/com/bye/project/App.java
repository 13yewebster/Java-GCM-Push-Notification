package com.bye.project;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bye.project.Content;

public class App extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ApiKey Developer
        String apiKey = "AIzaSyCIAQe6V-ztu8X_IS3FwMSwVVx3gmEPGPg";

        //Get Params
		String regId = request.getParameter("regId");
		String title = request.getParameter("title");
		String message = request.getParameter("message");

		//Create Data
		Content newPerson = new Content();
		newPerson.addRegId(regId);
		newPerson.createData(title, message);

		//Send To
        PostToGcm.post(request, response, apiKey, newPerson);

	}

}
