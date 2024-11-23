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

@WebServlet("/DepositMoney")
public class DepositMoneyInfo extends HttpServlet  {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   String accountnumber=req.getParameter("accountnumber");
		   String balance=req.getParameter("balance");
		   
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
				PreparedStatement ps=con.prepareStatement("update account set balance=balance+? where accountnumber=?");
				ps.setDouble(1, Double.parseDouble(balance));
				ps.setLong(2,Long.parseLong(accountnumber));
				ps.execute();
				con.close();
				
				PrintWriter pout=resp.getWriter();
				pout.println("account number : " +accountnumber);
				pout.println("Balance : " +balance);
				
				 RequestDispatcher rd=req.getRequestDispatcher("File4.html");
				   rd.forward(req, resp);
				
			            
				

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		   
		   
	}

}
