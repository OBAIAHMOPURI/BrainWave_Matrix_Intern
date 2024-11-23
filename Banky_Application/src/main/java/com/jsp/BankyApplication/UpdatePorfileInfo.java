package com.jsp.BankyApplication;

import java.io.IOException;
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

@WebServlet("/UpdateProfile")
public class UpdatePorfileInfo  extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String id=req.getParameter("id");
         //String accountnumber=req.getParameter("accountnumber");
         //String pincode=req.getParameter("pincode");
         //String balance=req.getParameter("balance");
         //String name=req.getParameter("name");
         String mobilenumber=req.getParameter("mobilenumber");
		
         try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
			PreparedStatement ps=con.prepareStatement("update account set mobilenumber=? where id=?");
			ps.setLong(1, Long.parseLong(mobilenumber));
			ps.setInt(2, Integer.parseInt(id));
            ps.execute();
            con.close();
            RequestDispatcher rd=req.getRequestDispatcher("File2.html");
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
