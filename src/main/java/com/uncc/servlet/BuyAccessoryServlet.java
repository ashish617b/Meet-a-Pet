package com.uncc.servlet;

import model.Accessory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by JOYTA on 05-11-2016.
 */
public class BuyAccessoryServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("reached BuyAccessoryServlet");
        String petType=request.getParameter("pettype");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/meetapet";
            String username="root";
            String password="root";
            Connection conn= DriverManager.getConnection(url,username,password);

            if(petType.equals("dog")){
                String query="select accessoryname,price from accessorydog";

                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery(query);

                ArrayList<Accessory> accessoryList = new ArrayList<Accessory>();

                while(rs.next()){
                    Accessory accessory = new Accessory();
                    accessory.setAccessoryName(rs.getString("accessoryname"));
                    accessory.setPrice(rs.getInt("price"));
                    accessoryList.add(accessory);
                }

                request.setAttribute("accessoryList",accessoryList);
                RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/Accessory.jsp");
                reqDispatcher.forward(request,response);

            }

            else{
            String query="select accessoryname,price from accessorycat";

            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(query);

            ArrayList<Accessory> accessoryList = new ArrayList<Accessory>();

            while(rs.next()){
                Accessory accessory = new Accessory();
                accessory.setAccessoryName(rs.getString("accessoryname"));
                accessory.setPrice(rs.getInt("price"));
                accessoryList.add(accessory);
            }

            request.setAttribute("accessoryList",accessoryList);
            RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/Accessory.jsp");
            reqDispatcher.forward(request,response);

        }
            }


           /* String query="select accessoryname,price from accessorydog";

            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(query);

            ArrayList<Accessory> accessoryList = new ArrayList<Accessory>();

            while(rs.next()){
                Accessory accessory = new Accessory();
                accessory.setAccessoryName(rs.getString("accessoryname"));
                accessory.setPrice(rs.getInt("price"));
                accessoryList.add(accessory);
            }

            request.setAttribute("accessoryList",accessoryList);
            RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/Accessory.jsp");
            reqDispatcher.forward(request,response);

        }*/
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    }
