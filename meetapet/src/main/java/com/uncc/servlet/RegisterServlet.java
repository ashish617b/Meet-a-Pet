package com.uncc.servlet;

/**
 * Created by JOYTA on 07-10-2016.
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by JOYTA on 06-10-2016.
 */



public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String password = request.getParameter("password");
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String add1=request.getParameter("add1");
        String add2=request.getParameter("add2");
        String contact=request.getParameter("contact");
        String email=request.getParameter("email");



        //boolean status;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/meetapet","root","root");
            PreparedStatement ps2;
            ps2 = con.prepareStatement("Select * FROM member where email = ?");
            ps2.setString(1, email);

            ResultSet resultSet=ps2.executeQuery();
            if(resultSet.next()) {

                response.sendRedirect("RegistrationUnSuccessful.html");
            }
            else {
            PreparedStatement ps=con.prepareStatement("insert into member(firstname,lastname,add1,add2,contact,email) values(?,?,?,?,?,?)");
            ps.setString(1,firstName);
            ps.setString(2,lastName);
            ps.setString(3,add1);
            ps.setString(4,add2);
            ps.setString(5,contact);
            ps.setString(6,email);

            PreparedStatement ps1 = con.prepareStatement("insert into login(username,password) values(?,?)");
            ps1.setString(1,email);
            ps1.setString(2,password);

            int rs=ps.executeUpdate();
            int rs1=ps1.executeUpdate();

                response.sendRedirect("userLogged.jsp"); //logged-in page
                out.close();

        }}
        catch(Exception e){System.out.println(e);}
    }
}
