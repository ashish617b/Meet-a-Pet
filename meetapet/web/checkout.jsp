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

            <td>DOG ID</td>
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
                String query="select d.dog_id,p.pet_type,d.breedname,d.sex,d.age,d.color,d.price from pet p right join dog d on p.pet_id=d.dog_id inner join cart c on c.dog_id = d.dog_id where user_id = 1;  ";

                Connection conn=DriverManager.getConnection(url,username,password);
                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery(query);
                while(rs.next())
                {

        %>
        <tr>
            <td><%=rs.getString("d.dog_id") %></td>
            <td><%=rs.getString("p.pet_type") %></td>
            <td><%=rs.getString("d.breedname")%></td>
            <td><%=rs.getString("d.sex") %></td>
            <td><%=rs.getInt("d.age") %></td>
            <td><%=rs.getString("d.color") %></td>
            <td><%=rs.getInt("d.price") %></td>
        </tr>
        <%

            }
        %>
    </table>
        <%
    rs.close();
    stmt.close();
    conn.close();
    }
catch(Exception e)
{
    e.printStackTrace();
    }


%>

    <a href="remove.jsp">Remove Item </a>
    <a href="addtcart.jsp">Go back</a>
    <a href="status.jsp">Check availibility</a>
</body>
</html>