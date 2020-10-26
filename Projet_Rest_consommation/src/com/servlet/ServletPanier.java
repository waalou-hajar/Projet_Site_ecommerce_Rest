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

import com.model.panier;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;




@WebServlet("/ServletPanier")
public class ServletPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ServletPanier() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Jsonb jsonb = JsonbBuilder.create();
	
		
		
		String nom=request.getParameter("nom");
		double prix=Double.parseDouble(request.getParameter("prix"));
		System.out.println(nom+prix);
		
		
	
		panier p= new panier(nom,prix);
		// desrialization objet panier 
		String jsonpanier=jsonb.toJson(p, panier.class);
		
		// l'ajout l'objet panier 
		
		if(p!=null) {
			try {
				URL url = new URL("http://localhost:8080/Site_Rest(Publication)/traitement/AjouterlivrePanier");
		        HttpURLConnection con  = (HttpURLConnection) url.openConnection();
		        con.setDoOutput(true);
		        con.setRequestMethod("POST");
		        con.setRequestProperty("Content-Type", "application/json");
//		        con.setRequestProperty("Accept", "application/json");
		        OutputStream os = con.getOutputStream();
		        os.write(jsonpanier.getBytes());
		        os.close();
		        os.flush();
		        System.out.println(jsonpanier.getBytes());
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
		
		PrintWriter out=response.getWriter();
	
		
		
		System.out.println(p.getId());
		System.out.println(p.getNom_livre());
		

      request.getRequestDispatcher("ListePanier").forward(request, response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	}

}
