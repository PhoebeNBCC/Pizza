<%-- 
    Document   : confirm
    Created on : 9-Dec-2019, 3:45:17 PM
    Author     : phoeb
--%>

<%@page import="Business.Pizza"%>
<%@page import="Business.CustomerBL"%>
<%@page import="Business.PizzaBL"%>
<%@page import="Business.ToppingsBackingBean"%>
<%@page import="DataBean.PizzaDL"%>
<%@page import="Business.Customer"%>
<%@page import="DataBean.CustomerDL"%>
<%@page import="Business.Toppings"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.Crust"%>
<%@page import="Business.Size"%>
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
                    <a class="nav-link menu" href="Login.xhtml">
                        Login</a>
                </li>



            </ul>

        </nav>
        <br><br><br><br>
        <h2>Confirm Pizza</h2>
        <% %>
        
        <!--
        Size: <%//=((Size)session.getAttribute("size")).getName() %><br>
        Crust: <%//=((Crust)session.getAttribute("crust")).getName() %><br>
        Toppings: <%//=Toppings.displayToppings(((ArrayList<Toppings>)session.getAttribute("toppings"))) %><br>
        -->


        <%
            String strSize = session.getAttribute("size").toString();
            int intSize = Integer.parseInt(strSize);
            String size = PizzaBL.getSize(intSize).getName();

            String[] toppings = (String[]) session.getAttribute("toppings");

            
            
            String id = session.getAttribute("customerId").toString();
            int custId = Integer.parseInt(id);
             
            //out.println(custId);
            Customer c = CustomerBL.getCustomerById(custId);
            String strHouseNo="";
            int houseNo=c.getHouseNo();
            if(houseNo!=0) { strHouseNo = Integer.toString(houseNo);}
            session.setAttribute("customer", c);
            ArrayList<Pizza> pizzas = (ArrayList<Pizza>)session.getAttribute("pizzaArray");
            int i=1;
            double totalOrder = 0;
            for (Pizza p : pizzas ){
                out.println("Pizza "+i+"<br>");
                i++;
                out.println("<label>Size: </label>"+ PizzaBL.getSize(p.getSizeId()).getName()+"<br>");
                out.println("<label>Crust: </label>"+ PizzaBL.getCrust(p.getCrustTypeId()).getName()+"<br>");
                out.println("<label>Toppings: </label>"+ToppingsBackingBean.displayToppingName(p.getToppings()) +"<br>");
                double price = Math.round(p.getPrice()*100);
                price = price/100;
                out.println("<label>Price: </label>$"+price +"<br>");
                totalOrder +=price;
            }
            double tax100 = Math.round(totalOrder*15);
            double tax = tax100/100;
            totalOrder +=tax;
            totalOrder = Math.round(totalOrder*100);
            totalOrder = totalOrder/100;
            out.println("<br><strong><label>Tax: </label>$"+ tax +"</strong><br>");
            out.println("<strong><label>Total order amount: </label>$"+totalOrder + "</strong><br>");
            
            session.setAttribute("total", totalOrder);
        %>
        <br><br>
        <h2>Customer information</h2>
        <label>First name:</label> <%=c.getfName()%><br>
        <label>Last name:</label> <%=c.getlName()%><br>
        <label>Phone Number:</label> <%=c.getPhone()%><br>
        <label>Email:</label>  <%=c.getEmail()%><br>
        <label>House Number:</label> <%=strHouseNo%><br>
        <label>Street:</label> <%=c.getStreet()%><br>
        <label>Province:</label> <%=c.getProvince()%><br>
        <label>Postal Code:</label> <%=c.getPostalCode()%><br>
        <br><br>
        <h2>Payment Method</h2>
         <form action="OrderProc">   
        <input type="radio" name="payment" value="COD" checked>Cash on Delivery<br>
        <input type="radio" name="payment" value="online" >Online<br><br>        
        <button type="submit">Confirm and Make Payment</button>
        </form>
    </body>
</html>
