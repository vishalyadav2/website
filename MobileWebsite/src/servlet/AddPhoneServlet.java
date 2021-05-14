package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name="AddPhoneServlet",urlPatterns = "/AddPhoneServlet")
@MultipartConfig(maxFileSize = 9999999999L)
public class AddPhoneServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String phonename=req.getParameter("phonename");
		String detail=req.getParameter("detail");
		float price=Float.parseFloat(req.getParameter("price"));
		String link=req.getParameter("link");
		Part part=req.getPart("image");
		String photo=req.getParameter("photo");
		String detailp=req.getParameter("detailp");
		InputStream is=part.getInputStream();

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moobile","root","");
			PreparedStatement ps=con.prepareStatement("insert into phone (phonename,detail,price,link,image,photo,detailp,status) values(?,?,?,?,?,?,?,?)");
			ps.setString(1, phonename);
			ps.setString(2, detail);
			ps.setFloat(3, price);
			ps.setString(4, link);
			ps.setBlob(5, is);
			ps.setString(6, photo);
			ps.setString(7, detailp);
			ps.setString(8, "A");
			ps.executeUpdate();
			resp.sendRedirect("phones.jsp");
		}
		catch(Exception e)
		{
			PrintWriter out=resp.getWriter();
			out.println(e);
		}
	}
}