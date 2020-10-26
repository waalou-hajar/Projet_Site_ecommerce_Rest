package com.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.WebTarget;

import com.model.User;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

@WebServlet("/Auth2")
public class Auth2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
    public Auth2() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//PrintWriter out = response.getWriter();
		String nom=request.getParameter("nom");
		String email=request.getParameter("email");
		//System.out.println(nom + email);
		//User u =port.isvalidlogin(email,nom);
		javax.ws.rs.client.Client client = javax.ws.rs.client.ClientBuilder.newClient();
		WebTarget target2 = client.target("http://localhost:8080/Site_Rest(Publication)/traitement/ValiderUser/"+email+"/"+nom);
	String contientobjetsousform_JSON=target2.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
	
	
	
	System.out.println(contientobjetsousform_JSON);
	
	
	
	
	
	
	if(!contientobjetsousform_JSON.equals("")) {
	Jsonb jsonb = JsonbBuilder.create();
	 User u=jsonb.fromJson(contientobjetsousform_JSON,User.class);
	
	
	
	
		
		
		
		   
			if(u.getNom().equals("hajar") &&  u.getEmail().equals("hajar@123"))
			{
				request.getRequestDispatcher("f.jsp").forward(request, response);	
		    }
		
			else {
				System.out.println(u.getEmail());
				
				HttpSession ses =request.getSession();
				ses.setAttribute("user", u);
			request.getRequestDispatcher("ServletValide").forward(request, response);	
				
		        }
	}
			else{
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
