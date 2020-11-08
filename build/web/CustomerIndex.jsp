<%-- 
    Document   : CustomerIndex
    Created on : 9-Dec-2019, 3:01:32 PM
    Author     : phoeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>P&P Pizza</title>
        <link href="bootstrap.min.css" rel="stylesheet">
        <link href="style.css" rel="stylesheet" >


        <link rel="icon" href="images/favicon.ico">
    </head>
    <body>

        <nav class="navbar navbar-toggleable-md navbar-inverse fixed-top">
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <ul class="navbar-nav mr-auto">
                <li>
                    <a class="navbar-brand" href="#"><img src="images/logo.png" class="logo"></a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link active menu" href="index.jsp">     
                        Home<span class="sr-only">(current)</span></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link menu" href="#">
                        Contact</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link menu" href="Login.xhtml">
                        Login</a>
                </li>



            </ul>

        </nav>
        <BR><BR><BR><BR>
        <div >
            
            <div >
                <button><a href="index.jsp">Add another pizza</a></button>
                <form id="customerInfo" action="CustomerProc">
                    <h1>Customer Information</h1>
                    <label>First name:</label> <input class="contactInput" type="text" name="firstname" id="firstname" size="20" required="true" placeholder="required"><br>
                    <label>Last name:</label> <input class="contactInput" type="text" name="lastname" id="lastname" size="20" required="true" placeholder="required"><br>
                    <label>Phone Number:</label> <input class="contactInput"  type="text" name="phone" id="phone" size="20" required="true" placeholder="required"><br>
                    <label>Email:</label> <input class="contactInput" type="text" name="email" id="email" size="20" required="true" placeholder="required"><br>
                    <label>House Number:</label> <input class="contactInput" type="text" name="houseNum" id="houseNum" size="20"><br>
                    <label>Street:</label> <input class="contactInput" type="text" name="street" id="street" size="20"><br>
                    <label>Province:</label> <input class="contactInput"  type="text" name="province" id="province" size="2"><br>
                    <label>Postal Code</label> <input class="contactInput" type="text" name="postalCode" id="postalCode" size="7"><br>
                    <h1>Delivery</h1><br>
                    <input type="radio" name="delivery" value="pickup" checked>Pickup<br><br>
                    <input type="radio" name="delivery" value="home delivery" >Home Delivery<br><br>
                    <button type="submit" id="customerInfo">Checkout</button>   
                </form>
                
                
            </div><!-- end row -->
        </div><!-- end container -->
        <% 
          //out.print(session.getAttribute("size"));
        %>
    </body>
</html>
