package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import others.Email;

@WebServlet("/SendOTPServlet")
public class SendOTPServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String email=req.getParameter("email");
		HttpSession hs=req.getSession();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moobile","root","");
			PreparedStatement ps=con.prepareStatement("select * from users where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();

			PrintWriter out=resp.getWriter();
			if(rs.next())
			{
				Random r=new Random();
				int randomnumber=r.nextInt(8888)+1000; 

				
				hs.setAttribute("randomnumber", randomnumber);
				hs.setAttribute("email", email);

				Email em=new Email(email,"Password Reset?","Otp is: "+randomnumber);
				em.sendEmail();

				/*
				 * out.println("" + "<script>" +
				 * "alert('Otp have been sent to your email account. Please check');" +
				 * "window.location='collectotp.jsp';" + "</script>");
				 */
				hs.setAttribute("msg", "Otp have been sent to your email account. Please check!!!");
				hs.setAttribute("pagename", "collectotp.jsp");
				hs.setAttribute("type", "success");
				resp.sendRedirect("popup.jsp");
			}
			else
			{
				/*
				 * out.println("" + "<script>" + "alert('Email is incorrect');" +
				 * "window.location='collectemail.jsp';" + "</script>");
				 */
				hs.setAttribute("msg", "Email is incorrect!!!");
				hs.setAttribute("pagename", "collectemail.jsp");
				hs.setAttribute("type", "error");
				resp.sendRedirect("popup.jsp");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}
}