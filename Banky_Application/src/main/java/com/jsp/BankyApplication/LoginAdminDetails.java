package com.jsp.BankyApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

@WebServlet("/LoginAdminDetails")
public class LoginAdminDetails extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		          String email=req.getParameter("email");
		          String password=req.getParameter("password");
		          
		           try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
					PreparedStatement ps=con.prepareStatement("select * from admin where email=? AND password=?" );
					ps.setString(1, email);
					ps.setString(2, password);
				   ResultSet rs=  ps.executeQuery();
				   
				   if(rs.next())
				   {
					   RequestDispatcher rd=req.getRequestDispatcher("OptionsForAdmin.html");
					   rd.forward(req, resp);
				   }
				   else
				   {
					   PrintWriter pout=resp.getWriter();
					   pout.print("<h1>Email or password not founded</h1>");
					   
					   RequestDispatcher rd=req.getRequestDispatcher("LoginAdmin.html");
					   rd.include(req, resp);
				   }
				   con.close();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          
	}

}
