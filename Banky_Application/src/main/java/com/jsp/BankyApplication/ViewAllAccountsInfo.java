package com.jsp.BankyApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewAllAccounts")
public class ViewAllAccountsInfo extends HttpServlet {
          @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        	  
    		 // String id=req.getParameter("id");
    		  
    		  
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
					PreparedStatement ps=con.prepareStatement("select * from bank.account");
					ResultSet rs=ps.executeQuery();
				    PrintWriter pout=resp.getWriter();
                      while(rs.next())
                        {
                	  
                    	  pout.println("id : "+(rs.getInt(1)));
          				  pout.println("accountnumber : "+(rs.getLong(2)));
          				  pout.println("pincode: "+(rs.getInt(3)));
          				  pout.println("balance : "+(rs.getDouble(4)));
          				  pout.println("name : "+(rs.getString(5)));
          				  pout.println("mobilenumber : "+(rs.getLong(6)));
          				  pout.println("==============================");
                    	}
                   
                      pout.println("All account details are viewed");
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
