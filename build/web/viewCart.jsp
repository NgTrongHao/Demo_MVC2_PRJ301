<%-- 
    Document   : viewCart
    Created on : Oct 13, 2023, 7:23:54 AM
    Author     : ngtronghao <ngtronghao02@gmail.com>
--%>

<%@page import="java.util.Map"%>
<%@page import="cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <%
            //1. Customer goes to cart place
            if (session != null) {
                //2. Customer take his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (String key : items.keySet()) {
                %>
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= key%>
                    </td>
                    <td>
                        <%= items.get(key)%>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
                        return;
                    }//end items have existed
                }//end cart has existed
            }//cart place must be existed
        %>
        <h2>No cart is existed</h2>
    </body>
</html>
