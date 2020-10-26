<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page  import="java.sql.*"%>
      <%@page import="java.util.ArrayList"%>
      <%@page import="com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<title> FIN d'achter </title>

 <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="theme-color" content="#03a6f3">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800,900" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/styles.css">
</head>

<body>
    <header>
        <div class="header-top">
            <div class="container">
                <div class="row">
                    <div class="col-md-3"><a href="#" class="web-url">www.bookstore.com</a></div>
                    <div class="col-md-6">
                        <h5>Free Shipping Over $99 + 3 Free Samples With Every Order</h5></div>
                    <div class="col-md-3">
                        <span class="ph-number">Call : 800 1234 5678</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="main-menu">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a class="navbar-brand" href="index.html"><img src="images/logo.png" alt="logo"></a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto">
                            <li class="navbar-item active">
                                <a href="index.html" class="nav-link">Home</a>
                            </li>
                            <li class="navbar-item">
                                <a href="shop.html" class="nav-link">Shop</a>
                            </li>
                            <li class="navbar-item">
                                <a href="about.html" class="nav-link">About</a>
                            </li>
                            <li class="navbar-item">
                                <a href="faq.html" class="nav-link">FAQ</a>
                            </li>
                            <li class="navbar-item">
                                <a href="login.html" class="nav-link">Login</a>
                            </li>
                        </ul>
                        <div class="cart my-2 my-lg-0">
                            <span>
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i></span>
                            <span class="quntity">3</span>
                        </div>
                        <form class="form-inline my-2 my-lg-0">
                            <input class="form-control mr-sm-2" type="search" placeholder="Search here..." aria-label="Search">
                            <span class="fa fa-search"></span>
                        </form>
                    </div>
                </nav>
            </div>
        </div>
    </header>


 <div style="border-style: groove;" align="center" style=margin:"25px 25px 25px 25px;" style="padding-bottom:4cm;" >
  <h2 style="color:green;" class="text-muted" style="padding-top:4cm;" > votre Facture : </h2>
  <div >
   <div style="margin-left:92%" style="margin-top:4cm;">
   <svg width="4em" height="4em" viewBox="0 0 16 16" class="bi bi-person-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg" >
                <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"/>
                <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                <path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"/>
              </svg>
              <%
              User client = (User) session.getAttribute("user");
          	
              
              
              %>
  </div>
        <p style="color:red;" align="right"> User :<u>${client.nom}</u></p>
        <p align="right" ><a href="/Projet_Rest_consommation/Deconnecter" > Déconnecter </a></p>
  </div>
  
  


<div ><b  style="padding-left:2cm;">les livres achetez sont :</b> <p color="green" >${livres_totale} </p>  </br> </br></div> 

<b  style="padding-left:2cm;">  le prix totale de votre Commande est :</b>${Prix_totale}  (DH)
</br>
</br>
</br>
</br>

<div style="padding-bottom:1cm;"><a href="/Projet_Rest_consommation/ServletCommande?nomClient=${client.nom}&prixtotale=${Prix_totale}" class="btn yellow" >Confirmer</a></div> 
 
 </div> 
 </br>
 </br>
</body>
</html>