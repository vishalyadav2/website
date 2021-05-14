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
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//Take the id which is coming from book.jsp page
				//using url rewriting technique
				int phoneid=Integer.parseInt(req.getParameter("phoneid"));
				HttpSession hs=req.getSession();
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moobile","root","");
					PreparedStatement ps=con.prepareStatement("update phone set status='D' where phoneid=?");
					ps.setInt(1, phoneid);
					ps.executeUpdate();

					PrintWriter out=resp.getWriter();
			/*
			 * out.println("" + "<script>" + "alert('Phone Deleted Successfully!!!');" +
			 * "window.location='phones.jsp';" + "</script>");
			 */
					hs.setAttribute("msg", "Phone Deleted Successfully!!!");
					hs.setAttribute("pagename", "phones.jsp");
					hs.setAttribute("type", "success");
					resp.sendRedirect("popup.jsp");
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
	}
}