<%-- 
    Document   : index
    Created on : 24-Nov-2019, 4:40:00 PM
    Author     : phoeb
--%>

<%@page import="Business.Toppings"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.ToppingsBackingBean"%>
<%@page import="Business.Pizza"%>
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
            <h1>Build you own pizza</h1><br>
            <img src="images/pizza.png" class="pizzaImg">


            <div >
                <form action="PizzaProc">   
                    <% Pizza p = new Pizza();

                    %>
                    <h3>Pizza Size</h3>
                    <div >
                        <input type="radio" name="size" value="1"> Personal - $6<br>
                        <input type="radio" name="size" value="2" checked> Medium - $10<br>
                        <input type="radio" name="size" value="3" > Large - $14<br>
                        <input type="radio" name="size" value="4"> Extra Large - $16
                    </div>
                    <br><br>
                    <h3 >Crust</h3>
                    <div >

                        <input type="radio" name="crust" value="1" checked> Thin Crust<br>
                        <input type="radio" name="crust" value="2"> Handmade Pan<br>
                        <input type="radio" name="crust" value="3"> Original<br>
                        <input type="radio" name="crust" value="4"> Gluten<br>
                        <input type="radio" name="crust" value="5"> Chicago Style<br>


                    </div>
                    <br><br>
                    <h3 >Toppings</h3>
                    <div>
                        <%     ArrayList<Toppings> ts = ToppingsBackingBean.FetchActiveToppings();
                            for (Toppings t : ts) {
                                double price = Math.round(t.getPrice() * 100);
                                price = price / 100;
                                out.println("<input type=\"checkbox\" name=\"topping\" value=\"" + t.getToppingId() + "\"> " + t.getName() + " - $" + price + "<br>");
                            }

                        %>
                    </div>
                    <br><br>

                   Number of pizza: <input type="text" id="noPizza" name="noPizza" value="1" size="20"><br><br>
                    <button type="submit" class="addCart">Add to Cart</button>
                </form>

            </div><!-- end row -->
        </div><!-- end container -->

    </body>
</html>
