<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <style>
    h1, h2, h3 {
        text-align: center;
    }
    </style>
    <title>header</title>
</head>
<body>
<h1>Today's Personalized Frontpage</h1>
<h2><%=new Date().toString()%>
</h2>
</body>
</html>
