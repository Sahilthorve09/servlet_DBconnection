package com.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/submitform")
public class Student extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phoneno");
		String pass = request.getParameter("password");
		
		long mobNo = Long.parseLong(phone);
		
		System.out.println(fname);
		System.out.println(email);
		System.out.println(mobNo);
		System.out.println(pass);
		
		PrintWriter out = response.getWriter();
		out.println("<h1>"+fname+"</h1>");
		out.println(email);
		out.println(mobNo);
		out.println(pass);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch1043","root","root");
			PreparedStatement ps = c.prepareStatement("insert into studentInfo(sname ,email ,phoneNo ,password) values(?,?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, email);
			ps.setLong(3, mobNo);
			ps.setString(4, pass);
			ps.executeUpdate();

		
			c.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
}
