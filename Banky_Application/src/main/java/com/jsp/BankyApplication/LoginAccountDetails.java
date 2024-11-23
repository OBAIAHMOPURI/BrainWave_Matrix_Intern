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

@WebServlet("/LoginAccountDetails")
public class LoginAccountDetails extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  String accountnumber=req.getParameter("accountnumber");
          String pincode=req.getParameter("pincode");
          
          try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
			PreparedStatement ps=con.prepareStatement("Select * from account where accountnumber=? AND pincode=?");
			ps.setLong(1,Long.parseLong(accountnumber));
			ps.setInt(2,Integer.parseInt(pincode) );
		   ResultSet rs=  ps.executeQuery(); 
		   if(rs.next())
		   {
			   RequestDispatcher rd=req.getRequestDispatcher("OptionsForAccount.html");
			   rd.forward(req, resp);
		   }
		   else
		   {
			   PrintWriter pout=resp.getWriter();
			   pout.print("<h1>Account number or pincode is wrong </h1>");
			   
			   RequestDispatcher rd=req.getRequestDispatcher("LoginAccount.html");
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

