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
public class RemoveFromCartServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);

        //String dogid=request.getParameter("cart");
        String username = (String) session.getAttribute("currentSessionUser");
        String items[]= request.getParameterValues("item1");
        int rs1=0;
        int rs2=0;
        //String result;

        //for (String item:items)
        //out.println("reached AddToCartServlet" + item + username);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/meetapet", "root", "root");

            //PreparedStatement ps1;
            PreparedStatement ps3;
            //ps1 = con.prepareStatement("Select * FROM add_to_cart_accessory_dog");


            //ResultSet resultSet= ps1.executeQuery();
            //if(resultSet.next()) {
            Statement stmt = con.createStatement();
            ResultSet ps2 = stmt.executeQuery("select c.accessoryname,c.price,c.accessory_id, a.order_id from accessorydog c\n" +
                    "INNER JOIN add_to_cart_accessory_dog a on a.accessory_id=c.accessory_id \n" +
                    "where a.order_id = (select max(order_id) from add_to_cart_accessory_dog) ;");

            while (ps2.next()) {
                rs1 = ps2.getInt("c.accessory_id");
                rs2= ps2.getInt("a.order_id");

            }
            //rs2 = rs1+1;

            out.println(rs2 + username);
            //rs = result.getInt(1);
            // }
            for (String item:items) {
                ps3 = con.prepareStatement("delete from add_to_cart_accessory_dog where order_id=? and email=? and accessory_id=?");

                ps3.setInt(1, rs2);
                ps3.setString(2, username);
                ps3.setString(3, item);
                int resultSet1 = ps3.executeUpdate();
                int item1 = Integer.parseInt(item);
                System.out.println("order_id" + "email" + "accessory_id");
                CallableStatement cStmt = con.prepareCall("{call update_accessorydog_remove(?)}");//call meetapet.update_accessorydog(5);
                cStmt.setInt(1, item1);
                cStmt.executeUpdate();

            }
            out.println("Record is updated");
            System.out.println(" answer" + username);
            response.sendRedirect("ThankYou.jsp");
        }
        catch(Exception e){System.out.println(e);}


    }
}
