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
public class InsertAccessoryServlet extends HttpServlet {
        public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String accessoryName=request.getParameter("accessoryName");
            String price=request.getParameter("price");
            String quantity=request.getParameter("quantity");
            String accessorypetType=request.getParameter("accessorypettype");

            //boolean status;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/meetapet","root","root");

                PreparedStatement ps;
                PreparedStatement ps1;

                if(accessorypetType.equals("dog")){
                    ps1 = con.prepareStatement("Select * FROM accessorydog where accessoryname = ?");
                    ps1.setString(1, accessoryName);
                }
                else{
                    ps1 = con.prepareStatement("Select * FROM accessorycat where accessoryname = ?");
                    ps1.setString(1, accessoryName);
                }

                //System.out.println("pet type = "+petType);
                ResultSet resultSet=ps1.executeQuery();
                if(resultSet.next()) {

                    response.sendRedirect("InsertAccessoryUnSuccessfully.html");
                }


                else{
                    if(accessorypetType.equals("dog")) {
                        ps = con.prepareStatement("insert into accessorydog(accessoryname,price,quantity) values(?,?,?)");
                        ps.setString(1, accessoryName);
                        ps.setString(2, price);
                        ps.setString(3, quantity);

                    }
                    else{
                        ps = con.prepareStatement("insert into accessorycat(accessoryname,price,quantity) values(?,?,?,)");
                        ps.setString(1, accessoryName);
                        ps.setString(2, price);
                        ps.setString(3, quantity);

                    }

                    int rs=ps.executeUpdate();
                    response.sendRedirect("InsertAccessorySuccessfully.html");


                }

                out.close();

            }catch(Exception e){System.out.println(e);}
        }
    }
