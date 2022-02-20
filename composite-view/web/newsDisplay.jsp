<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.ltyocg.compositeview.ClientPropertiesBean" %>
<html>
<head>
    <style>
    h1, h2, h3, tr, td {
        text-align: center;
    }

    .centerTable {
        margin-left: auto;
        margin-right: auto;
    }

    table {
        border: 1px solid black;
    }
    </style>
    <title>newsDisplay</title>
</head>
<body>
<%ClientPropertiesBean propertiesBean = (ClientPropertiesBean) request.getAttribute("properties");%>
<h1>Welcome <%= propertiesBean.getName()%>
</h1>
<jsp:include page="header.jsp"/>
<table class="centerTable">
    <tr>
        <td></td>
        <% if (propertiesBean.isWorldNewsInterest()) { %>
        <td>
            <%@include file="worldNews.jsp" %>
        </td>
        <% } else { %>
        <td>
            <%@include file="localNews.jsp" %>
        </td>
        <% } %>
        <td></td>
    </tr>
    <tr>
        <% if (propertiesBean.isBusinessInterest()) { %>
        <td>
            <%@include file="businessNews.jsp" %>
        </td>
        <% } else { %>
        <td>
            <%@include file="localNews.jsp" %>
        </td>
        <% } %>
        <td></td>
        <% if (propertiesBean.isSportsInterest()) { %>
        <td>
            <%@include file="sportsNews.jsp" %>
        </td>
        <% } else { %>
        <td>
            <%@include file="localNews.jsp" %>
        </td>
        <% } %>
    </tr>
    <tr>
        <td></td>
        <% if (propertiesBean.isScienceNewsInterest()) { %>
        <td>
            <%@include file="scienceNews.jsp" %>
        </td>
        <% } else { %>
        <td>
            <%@include file="localNews.jsp" %>
        </td>
        <% } %>
        <td></td>
    </tr>
</table>
</body>
</html>
