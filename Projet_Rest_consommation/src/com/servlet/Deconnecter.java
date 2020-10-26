package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Deconnecter")
public class Deconnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Deconnecter() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user")!=null) {
			session.invalidate();
			
			System.out.println("session est vide");
			
			
			try {
				URL url = new URL("http://localhost:8080/Site_Rest(Publication)/traitement/ViderPanier");
		        HttpURLConnection con  = (HttpURLConnection) url.openConnection();
		        con.setDoOutput(true);
		        con.setRequestMethod("DELETE");
		        con.setRequestProperty("Accept", "application/json");
		        con.setRequestProperty("Content-Type", "application/json");
		    	BufferedReader br = new BufferedReader(new InputStreamReader(
						(con.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}
				con.disconnect();
			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			 }
			
			this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}else {
			System.out.println("session is  null");
			try {
				URL url = new URL("http://localhost:8080/Site_Rest(Publication)/traitement/ViderPanier");
		        HttpURLConnection con  = (HttpURLConnection) url.openConnection();
		        con.setDoOutput(true);
		        con.setRequestMethod("DELETE");
		        con.setRequestProperty("Accept", "application/json");
		        con.setRequestProperty("Content-Type", "application/json");
		    	BufferedReader br = new BufferedReader(new InputStreamReader(
						(con.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}
				con.disconnect();
			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			 }
			this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}
			
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
