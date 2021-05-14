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

@WebServlet("/UpdatePhoneServlet")
public class UpdatePhoneServlet extends HttpServlet 
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int phoneid=Integer.parseInt(request.getParameter("phoneid"));
		String phoneName= request.getParameter("phonename");
		String detail= request.getParameter("detail");
		String link= request.getParameter("link");
		Float price=Float.parseFloat(request.getParameter("price"));
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moobile","root","");
			PreparedStatement ps=con.prepareStatement("update phone set phonename=?,detail=?,price=?,link=? where phoneid=?");
			ps.setString(1, phoneName);
			ps.setString(2, detail);
			ps.setFloat(3, price);
			ps.setString(4, link);
			ps.setInt(5, phoneid);
			ps.executeUpdate();
			response.sendRedirect("phones.jsp");
		}
		catch(Exception e)
		{
			PrintWriter out=response.getWriter();
			out.println(e);
		}

	}

}