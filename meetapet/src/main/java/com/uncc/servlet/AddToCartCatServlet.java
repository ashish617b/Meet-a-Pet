package com.uncc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by JOYTA on 20-11-2016.
 */
public class AddToCartCatServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);

        //String dogid=request.getParameter("cart");
        String username = (String) session.getAttribute("currentSessionUser");
        String items[]= request.getParameterValues("item");
        int rs1=0;
        int rs2=0;
        //String result;

        //for (String item:items)
        //out.println("reached AddToCartServlet" + item + username);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/meetapet", "root", "root");

            //PreparedStatement ps1;
            PreparedStatement ps2;
            PreparedStatement ps3;
            //ps1 = con.prepareStatement("Select * FROM add_to_cart_accessory_cat");

            //ResultSet resultSet= ps1.executeQuery();
            //if(resultSet.next()) {
            ps2 = con.prepareStatement("select max(order_id)from add_to_cart_accessory_cat");
            ResultSet result = ps2.executeQuery();
            while (result.next()) {
                rs1 = result.getInt(1);

            }
            rs2 = rs1+1;

            //out.println(rs2 + username);
            //rs = result.getInt(1);
            // }
            for (String item:items) {
                ps3 = con.prepareStatement("insert into add_to_cart_accessory_cat(order_id,accessory_id,email,quantity) values(?,?,?,?)");
                ps3.setInt(1, rs2);
                ps3.setString(2, item);
                ps3.setString(3, username);
                ps3.setInt(4, 1);
                int resultSet1 = ps3.executeUpdate();
                int item1 = Integer.parseInt(item);
                CallableStatement cStmt = con.prepareCall("{call update_accessorycat_insert(?)}");//call meetapet.update_accessorydog(5);
                cStmt.setInt(1, item1);
                cStmt.executeUpdate();

            }
            //out.println("Record is updated");
            System.out.println(" answer" + username);
            response.sendRedirect("CartAccessoryCat.jsp");

            out.close();
        }
        catch(Exception e){System.out.println(e);}


    }
}
