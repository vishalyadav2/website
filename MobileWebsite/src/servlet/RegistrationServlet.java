package servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import others.Email;



@WebServlet(name="RegistrationServlet",urlPatterns = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet 
{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		String Username=req.getParameter("username");
		String email=req.getParameter("email");
		
		String Password=req.getParameter("password");

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moobile","root","");
			PreparedStatement ps=con.prepareStatement("insert into users values(?,?,?,?)");
			ps.setString(1,Username);
			ps.setString(2,email);
			ps.setString(3,Password);
			ps.setString(4, "user");
			
			ps.executeUpdate();
			
		Email em=new Email(email,"Registerd Successfully!!!", "Welcome to Phone Website!!!");
		em.sendEmail();

			
			resp.sendRedirect("login.jsp");
		} 
		catch(Exception e)
		{
			PrintWriter out=resp.getWriter();
			out.println(e+"");
		}
		
	}
}