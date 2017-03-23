<%--
  Created by IntelliJ IDEA.
  User: Ashish
  Date: 20/11/2016
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ page import="java.sql.Connection" %>
    <%@page import="java.sql.DriverManager" %>
    <%@page import="java.sql.Statement"%>
    <%@ page import="java.sql.ResultSet" %>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<form method="post">

    <table border="2">
        <tr>

            <td>CAT ID</td>
            <td>PET TYPE</td>
            <td>BREED NAME</td>
            <td>Sex </td>
            <td>Age</td>
            <td>Color</td>
            <td>Price</td>

        </tr>
        <%
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                String url="jdbc:mysql://localhost:3306/meetapet";
                String username="root";
                String password="root";

                String query="select c.cat_id,p.pet_type,c.breedname,c.sex,c.age,c.color,c.price from pet1 p right join cat c on p.pet_id=c.cat_id inner join cart1 m on m.cat_id = c.cat_id where user_id = 1;  ";

                Connection conn=DriverManager.getConnection(url,username,password);
                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery(query);
                while(rs.next())
                {

        %>
        <tr>
            <td><%=rs.getString("c.cat_id") %></td>
            <td><%=rs.getString("p.pet_type") %></td>
            <td><%=rs.getString("c.breedname")%></td>
            <td><%=rs.getString("c.sex") %></td>
            <td><%=rs.getInt("c.age") %></td>
            <td><%=rs.getString("c.color") %></td>
            <td><%=rs.getInt("c.price") %></td>
        </tr>
        <%

            }
        %>
        <%
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


        %>

        <a href="removecat.jsp">Remove Item </a>
        <a href="addcatcart.jsp">Go back</a>
    </table>
    <table>
        <tr>
            <td>CAT ID</td>
            <td>STATUS</td>
        </tr>

        <%
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                String url="jdbc:mysql://localhost:3306/meetapet";
                String username="root";
                String password="root";
                String q1 = "select s.cat_id, s.status from catava s left join cart1 c on c.cat_id = s.cat_id where user_id = 1";
                Connection conn=DriverManager.getConnection(url,username,password);
                Statement stmt=conn.createStatement();
                ResultSet rs1= stmt.executeQuery(q1);
                while(rs1.next())
                {

        %>
        <tr>
            <td><%=rs1.getString("s.cat_id") %></td>
            <td><%=rs1.getString("s.status") %></td>
        </tr>
        <%

            }
        %>
        <%
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


        %>

    </table>
</body>
</html>
