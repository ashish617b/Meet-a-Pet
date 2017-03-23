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
 * Created by JOYTA on 11-10-2016.
 */

public class InsertPetServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String breedName=request.getParameter("breedName");
        String sex=request.getParameter("sex");
        String age=request.getParameter("age");
        String color=request.getParameter("color");
        String price=request.getParameter("price");
        String petType=request.getParameter("pettype");
        String quantity=request.getParameter("quantity");

        //boolean status;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/meetapet","root","root");

            PreparedStatement ps;
            PreparedStatement ps1;

            if(petType.equals("dog")){
                ps1 = con.prepareStatement("Select * FROM dog where breedname = ?");
                ps1.setString(1, breedName);
            }
            else{
                ps1 = con.prepareStatement("Select * FROM cat where breedname = ?");
                ps1.setString(1, breedName);
            }

            //System.out.println("pet type = "+petType);
            ResultSet resultSet=ps1.executeQuery();
            if(resultSet.next()) {

                response.sendRedirect("InsertPetUnSuccessfully.html");
            }


            else{
                    if(petType.equals("dog")) {
                        ps = con.prepareStatement("insert into dog(breedname,sex,age,color,price,quantity) values(?,?,?,?,?,?)");
                        ps.setString(1, breedName);
                        ps.setString(2, sex);
                        ps.setString(3, age);
                        ps.setString(4, color);
                        ps.setString(5, price);
                        ps.setString(6, quantity);
                    }
                    else{
                        ps = con.prepareStatement("insert into cat(breedname,sex,age,color,price,quantity) values(?,?,?,?,?,?)");
                        ps.setString(1, breedName);
                        ps.setString(2, sex);
                        ps.setString(3, age);
                        ps.setString(4, color);
                        ps.setString(5, price);
                        ps.setString(6,quantity);
                    }

                    int rs=ps.executeUpdate();
                    response.sendRedirect("InsertPetSuccessfully.html");

                    //HttpSession session = request.getSession(true);
                    //session.setAttribute("currentSessionUser",firstName + lastName);
                    //response.sendRedirect("userLogged.jsp"); //logged-in page
                }
                //HttpSession session = request.getSession(true);
                //session.setAttribute("currentSessionUser",firstName + lastName);
                //response.sendRedirect("invalidUser.jsp"); //logged-in page
            out.close();

        }catch(Exception e){System.out.println(e);}
    }
}

