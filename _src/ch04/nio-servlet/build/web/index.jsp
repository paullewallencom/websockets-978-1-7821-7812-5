<%-- 
    Document   : index
    Created on : Oct 20, 2013, 2:54:10 PM
    Author     : masoud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Submit the form and the information will go to NIO servlet for async operation</p>
        <form action="book-servlet" method="post">
            <input type="submit" value="submit">	
        </form>
    </body>
</html>
