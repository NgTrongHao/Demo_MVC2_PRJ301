<%-- 
    Document   : createAccount
    Created on : Oct 13, 2023, 9:14:46 AM
    Author     : ngtronghao <ngtronghao02@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="DispatchServlet" method="POST">
            Username * <input type="text" name="txtUsername" value="" />(6 - 30 chars)<br/>
            Password * <input type="password" name="txtPassword" value="" />(6 - 20 chars)<br/>
            Password * <input type="password" name="txtConfirm" value="" />(6 - 20 chars)
            Full name * <input type="text" name="txtFullName" value="" />(6 - 50 chars)<br/>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
