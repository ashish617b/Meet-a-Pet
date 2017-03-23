package com.uncc.servlet;

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
 * Created by JOYTA on 21-10-2016.
 */
public class AdminLoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userName=request.getParameter("userName");
        String password=request.getParameter("password");



        boolean status;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/meetapet","root","root");

            PreparedStatement ps=con.prepareStatement("select * from adminlogin where username=? and password=?");
            ps.setString(1,userName);
            ps.setString(2,password);

            //System.out.println("username "+userName);
            //System.out.println("password "+password);
            ResultSet rs=ps.executeQuery();
            //status=rs.next();

            //System.out.println("retrieved from DB "+rs.getString(1));
            //System.out.println("Retrieved from DB "+rs.getString(2));

            if(rs.next()) {
                //out.println("Hello " + userName);

                HttpSession session = request.getSession(true);
                //session.setAttribute("currentSessionUser",userName);
                response.sendRedirect("admin.html"); //logged-in page
            }
            else{
                //out.print("invalid userName/password.");
                //HttpSession session = request.getSession(true);
                //session.setAttribute("currentSessionUser",userName);
                response.sendRedirect("invalidUser.jsp"); //logged-in page
            }
            out.close();

        }
        catch(Exception e){System.out.println(e);}
    }
}
