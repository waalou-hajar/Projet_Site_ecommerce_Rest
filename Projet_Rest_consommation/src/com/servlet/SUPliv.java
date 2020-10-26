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
import com.model.User;





@WebServlet("/SUPliv")
public class SUPliv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
    public SUPliv() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id=Integer.parseInt(request.getParameter("id"));
		try {
			URL url = new URL("http://localhost:8080/Site_Rest(Publication)/traitement/SupprimerlivreP/"+id);
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
		Client client = javax.ws.rs.client.ClientBuilder.newClient();
		WebTarget targeto = client.target("http://localhost:8080/Site_Rest(Publication)/traitement/utilisateurs");
		String str=targeto.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
		ObjectMapper mapper = new ObjectMapper();
		
		FileInputStream fileInputStream1=null;
		try {
			FileWriter file = new FileWriter("json1.json");
			file.write(str);
			file.close();
			fileInputStream1 = new FileInputStream("json1.json");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<User> lp = mapper.readValue(fileInputStream1, new TypeReference<List<User>>(){});
		
		for (User p : lp) {
			System.out.println(p.getId());
		}
		request.setAttribute("listePanier", lp);
		
	
		request.getRequestDispatcher("les_livrespanier.jsp").forward(request, response);	
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
