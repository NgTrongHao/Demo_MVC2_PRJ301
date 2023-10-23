<%-- 
    Document   : bookStore
    Created on : Oct 19, 2023, 3:45:22 PM
    Author     : ngtronghao <ngtronghao02@gmail.com>
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <form action="DispatchServlet">
            <c:set var="store" value="${sessionScope.STORE}"/>
            Choose your book <select name="ddlBook">
                <c:if test="${not empty store}">
                    <c:forEach items="${store}" var="productDTO">
                        <option>${productDTO.productName}</option>
                    </c:forEach>
                </c:if>
            </select>
            <input type="text" name="bookQuantity" value="" /><br/>
            <input type="submit" value="Add Book to Your Cart" name="btAction">
            <input type="submit" value="View Your Cart" name="btAction">
        </form>
    </body>
</html>
<%--<form action="DispatchServlet">
            Choose your book <select name="ddlBook">
                <%
                    List<ProductDTO> products = (ArrayList<ProductDTO>) session.getAttribute("STORE");
                    if (products != null) {
                        for (ProductDTO product : products) {
                %>
                <option><%= product.getProductName()%></option>
                <%
                        }
                    }
                %>
            </select>
            <input type="text" name="bookQuantity" value="" /><br/>
            <input type="submit" value="Add Book to Your Cart" name="btAction">
            <input type="submit" value="View Your Cart" name="btAction">
        </form>--%>