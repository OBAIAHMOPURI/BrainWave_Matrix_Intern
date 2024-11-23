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

@WebServlet("/WithDrawBalance")
public class WithDrawBalanceInfo extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accountnumber=req.getParameter("accountnumber");
	      String balance=req.getParameter("balance");

	      try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
			
			PreparedStatement ps1=con.prepareStatement("select * from account ");
			ResultSet rs=ps1.executeQuery();
			rs.next();
			if(rs.getDouble(4)>=Double.parseDouble(balance))
			{
			
			PreparedStatement ps=con.prepareStatement("update account set balance=balance-? where accountnumber=?");
			ps.setDouble(1,Double.parseDouble(balance));
			ps.setLong(2, Long.parseLong(accountnumber));
            ps.execute();
            con.close();
            PrintWriter pout=resp.getWriter();
            pout.println(" Withdraw balance is successfully : " +balance);
               
			}
			else
			{
				 PrintWriter pout=resp.getWriter();
				 pout.println("<h1> we have Insufficent balance try agaian <h1> ");
				 
				 RequestDispatcher rd=req.getRequestDispatcher("WithDrawBalance.html");
				 rd.include(req, resp);
				
				
			}

			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
