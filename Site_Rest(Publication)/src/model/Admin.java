package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Admin implements Serializable{
	//attribut 
	private int id;
    private String nomAdmin ;
    private String emailAdmin; 
 //  get set 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomAdmin() {
		return nomAdmin;
	}
	public void setNomAdmin(String nomAdmin) {
		this.nomAdmin = nomAdmin;
	}
	public String getEmailAdmin() {
		return emailAdmin;
	}
	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}
	public Admin(String nomAdmin, String emailAdmin) {
		super();
		this.nomAdmin = nomAdmin;
		this.emailAdmin = emailAdmin;
	}
	public Admin(User u) {
		this.nomAdmin=u.getNom();
		this.emailAdmin=u.getEmail();
		// TODO Auto-generated constructor stub
	}
    
	
    

}
