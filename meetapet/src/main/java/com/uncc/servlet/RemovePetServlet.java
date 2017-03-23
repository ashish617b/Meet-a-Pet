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
 * Created by Ashish on 23/10/2016.
 */
public class RemovePetServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String breedName=request.getParameter("breedName");
        String petType=request.getParameter("pettype");
        PreparedStatement ps;
        PreparedStatement ps1;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/meetapet","root","root");

            if(petType.equals("dog")){
                ps1 = con.prepareStatement("Select * FROM dog where breedname = ?");
                ps1.setString(1, breedName);
            }
            else{
                ps1 = con.prepareStatement("Select * FROM cat where breedname = ?");
                ps1.setString(1, breedName);
            }

            ResultSet resultSet=ps1.executeQuery();
            if(resultSet.next()) {

                if (petType.equals("dog")) {
                    ps = con.prepareStatement("DELETE FROM dog where breedname = ?");
                    ps.setString(1, breedName);
                } else {
                    ps = con.prepareStatement("DELETE FROM cat where breedname = ?");
                    ps.setString(1, breedName);
                }

                int result = ps.executeUpdate();

                response.sendRedirect("removepetsuccessfully.html");
            }
            else{
                response.sendRedirect("removepetunsuccessful.html");
            }

        }catch(Exception e){System.out.println(e);}

        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }


}
