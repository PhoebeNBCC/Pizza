<%-- 
    Document   : OrderInfo
    Created on : 9-Dec-2019, 10:29:51 PM
    Author     : phoeb
--%>

<%@page import="Business.OrderBackingBean"%>
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
        <br><br><br><br>
        <% int orderId=Integer.parseInt(session.getAttribute("orderId").toString()); %>
        <h1>We are processing your order</h1>
        The order will be available on <%=OrderBackingBean.updateDeliveryDateTime(orderId) %>
    </body>
</html>
