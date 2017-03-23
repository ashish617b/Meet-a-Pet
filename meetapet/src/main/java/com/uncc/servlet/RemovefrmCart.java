package com.uncc.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemovefrmCart
 */
//@WebServlet("/RemovefrmCart")
public class RemovefrmCart extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String dog_id = request.getParameter("atc");
        System.out.println(dog_id);
        // please get the username using http session
        // use sql query to get the user_id
        int u_id =1;

        int status=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/meetapet","root","root");

            PreparedStatement ps=con.prepareStatement("delete from cart where user_id = '"+u_id+"' and dog_id = '"+dog_id+"' ");


            //System.out.println("username "+userName);
            //System.out.println("password "+password);
            status =ps.executeUpdate();
            //status=rs.next();

            //System.out.println("retrieved from DB "+rs.getString(1));
            //System.out.println("Retrieved from DB "+rs.getString(2));

            if(status ==1) {
                //out.println("Hello " + userName);
                response.sendRedirect("checkout.jsp");

            }
            else{
                //out.print("invalid userName/password.");
                //HttpSession session = request.getSession(true);
                //session.setAttribute("currentSessionUser",userName);
                response.sendRedirect("invalidUser.jsp"); //logged-in page
            }
            // out.close();

        }
        catch(Exception e){System.out.println(e);}
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */


}
