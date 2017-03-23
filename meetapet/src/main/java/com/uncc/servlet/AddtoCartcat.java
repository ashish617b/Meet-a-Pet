package com.uncc.servlet;

/**
 * Created by Ashish on 21/11/2016.
 */



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
       /* import java.io.IOException;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
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

/**
 * Servlet implementation class AddtoCart
 */
//@WebServlet("/AddtoCart")
public class AddtoCartcat extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddtoCartcat() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String cat_id = request.getParameter("atc");
       // System.out.println(dog_id);
        // please get the username using http session
        // use sql query to get the user_id
        int u_id =1;

        int status;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/meetapet","root","root");

            PreparedStatement ps=con.prepareStatement("insert into cart1 (cat_id , user_id) values ('"+cat_id+"','"+u_id+"') ");


            //System.out.println("username "+userName);
            //System.out.println("password "+password);
            status =ps.executeUpdate();
            //status=rs.next();

            //System.out.println("retrieved from DB "+rs.getString(1));
            //System.out.println("Retrieved from DB "+rs.getString(2));

            if(status ==1) {
                //out.println("Hello " + userName);
                response.sendRedirect("addcatcart.jsp");

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}


