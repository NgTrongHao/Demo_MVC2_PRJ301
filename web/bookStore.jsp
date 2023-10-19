<%-- 
    Document   : bookStore
    Created on : Oct 11, 2023, 8:30:28 AM
    Author     : ngtronghao <ngtronghao02@gmail.com>
--%>

<%@page import="product.ProductCart"%>
<%@page import="java.util.List"%>
<%@page import="product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <!--        <form action="DispatchServlet">
                    Choose your book <select name="ddlBook">
                        <option>Java</option>
                        <option>JDK</option>
                        <option>NetBeans</option>
                        <option>Servlet</option>
                        <option>Tomcat</option>
                        <option>JSP</option>
                        <option>JavaBeans</option>
                        <option>EL</option>
                    </select>
                    <input type="text" name="bookQuantity" value="" /><br/>
                    <input type="submit" value="Add Book to Your Cart" name="btAction">
                    <input type="submit" value="View Your Cart" name="btAction">
                </form>-->

        <%
            if (session != null) {
                ProductCart products = (ProductCart) session.getAttribute("PRODUCTS");
                if (products != null) {
                    Map<String, ProductDTO> items = products.getProducts();
                    if (items != null) {
        %>
        <form action="DispatchServlet">
            Choose your book <select name="ddlBook">
                <%
                    for (ProductDTO product : items.values()) {
                %>
                <option><%= product.getName()%></option>
                <%
                    }
                %>
            </select>
            <input type="text" name="bookQuantity" value="" /><br/>
            <input type="submit" value="Add Book to Your Cart" name="btAction">
            <input type="submit" value="View Your Cart" name="btAction">
        </form>
        <%
                    }
                }
            }
        %>
    </body>
</html>
