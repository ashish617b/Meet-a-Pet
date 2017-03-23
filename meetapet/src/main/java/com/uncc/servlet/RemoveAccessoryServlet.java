package com.uncc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by JOYTA on 31-10-2016.
 */
public class RemoveAccessoryServlet extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String accessoryName=request.getParameter("accessoryName");
            String accessorypetType=request.getParameter("accessorypettype");
            PreparedStatement ps;
            PreparedStatement ps1;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/meetapet","root","root");

                if(accessorypetType.equals("dog")){
                    ps1 = con.prepareStatement("Select * FROM accessorydog where accessoryname = ?");
                    ps1.setString(1, accessoryName);
                }
                else{
                    ps1 = con.prepareStatement("Select * FROM accessorycat where accessoryname = ?");
                    ps1.setString(1, accessoryName);
                }

                ResultSet resultSet=ps1.executeQuery();
                if(resultSet.next()) {

                    if (accessorypetType.equals("dog")) {
                        ps = con.prepareStatement("DELETE FROM accessorydog where accessoryname = ?");
                        ps.setString(1, accessoryName);
                    } else {
                        ps = con.prepareStatement("DELETE FROM accessorycat where accessoryname = ?");
                        ps.setString(1, accessoryName);
                    }

                    int result = ps.executeUpdate();

                    response.sendRedirect("removeaccessorysuccessfully.html");
                }
                else{
                    response.sendRedirect("removeaccessoryunsuccessful.html");
                }

            }catch(Exception e){System.out.println(e);}

            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }


    }
