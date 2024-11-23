package com.jsp.BankyApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddAdmin")
public class AddAdminInfo  extends HttpServlet{
               @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         		  String id=req.getParameter("id");
            	   String email=req.getParameter("email");
 		          String password=req.getParameter("password");
 		          String name=req.getParameter("name");

 		          
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
						PreparedStatement ps=con.prepareStatement("insert into admin(id,name,email,password) values(?,?,?,?)");
						ps.setInt(1, Integer.parseInt(id));
						ps.setString(2, name);
						ps.setString(3, email);
						ps.setString(4, password);
						ps.execute();
						con.close();
						
						
						   
						   RequestDispatcher rd=req.getRequestDispatcher("File3.html");
						   rd.include(req, resp);

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

            }
}
