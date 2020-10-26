package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.Commande;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;





@WebServlet("/ServletCommande")
public class ServletCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ServletCommande() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		
		String nom=request.getParameter("nomClient");
		double prix=Double.parseDouble(request.getParameter("prixtotale"));
		System.out.println(nom+prix);
		Commande c= new Commande(nom,prix);
		if(c!=null) {
			Jsonb jsonb = JsonbBuilder.create();
			String objetserialise_JSON = jsonb.toJson(c);
			// POST 
			try {
				URL url = new URL("http://localhost:8080/Site_Rest(Publication)/traitement/Ajoutercommande");
		        HttpURLConnection con  = (HttpURLConnection) url.openConnection();
		        con.setDoOutput(true);
		        con.setRequestMethod("POST");
		        con.setRequestProperty("Content-Type", "application/json");
	  	      con.setRequestProperty("Accept", "application/json");
		        OutputStream os = con.getOutputStream();
		        os.write(objetserialise_JSON.getBytes());
		        os.close();
		        os.flush();
		        System.out.println(objetserialise_JSON.getBytes());
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
			
		
		}
		else {
		      request.getRequestDispatcher("login.html").forward(request, response);
		}
	      request.getRequestDispatcher("shop.html").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
