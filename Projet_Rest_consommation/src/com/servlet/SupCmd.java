package com.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Commande;
import com.model.User;


@WebServlet("/SupCmd")
public class SupCmd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SupCmd() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		int id=Integer.parseInt(request.getParameter("id"));
		try {
			URL url = new URL("http://localhost:8080/Site_Rest(Publication)/traitement/SupprimerCommande/"+id);
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
		
		
		request.getRequestDispatcher("afficherCommande").forward(request, response);	
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
