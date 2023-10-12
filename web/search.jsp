<%-- 
    Document   : search
    Created on : Sep 29, 2023, 8:57:54 AM
    Author     : ngtronghao <ngtronghao02@gmail.com>
--%>

<%@page import="registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                Cookie newestCookie = cookies[cookies.length - 1];
                String username = newestCookie.getName();
        %>
        <font color="red">
            Welcome, <%= username%>
        </font>
        <%
            }//end cookies have existed
%>
        <h1>Search Page Result</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue"
                                value="<%= request.getParameter("txtSearchValue")%>" /><br/>
            <input type="submit" value="Search" name="btAction" />
            <input type="submit" value="LogOut" name="btAction" />

        </form>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");    //ep kieu tuong minh do attribute dang kieu bytestring
                //render
                if (result != null) {    //has one or more records
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>FullName</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchServlet"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;
                %>
            <form action="DispatchServlet" method="POST">
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="textUsername" 
                               value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" 
                               value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getFullName()%>
                    </td>
                    <td>
                        <input type="checkbox" name="checkAdmin" value="ON" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }
                               %>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="hidden" name="lastSearchValue" 
                               value="<%= searchValue%>" />
                        <input type="submit" value="Update" name="btAction" />
                    </td>
                </tr>
            </form>
            <%
                }   //end traverse DTO
            %>
        </tbody>
    </table>

    <%
    } else {    //no records
    %>
    <h2>
        No record is matched!!!
    </h2>
    <%
            }
        }//end searchValue did not trigger from previous form
%>
</body>
</html>
