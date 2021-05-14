package servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import model.user;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
	/**
	 *
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		user u = new user();
		u.setUsername(req.getParameter("username"));
		u.setPassword(req.getParameter("password"));
		HttpSession hs = req.getSession();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moobile", "root", "");
			PreparedStatement ps = con.prepareStatement("select * from users where username=? and password=?");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ResultSet rs = ps.executeQuery();

			PrintWriter out = resp.getWriter();
			if (rs.next()) {
				u.setEmail(rs.getString("email"));
				u.setRole(rs.getString("role"));

				
				// hs.setMaxInactiveInterval(60*2);
				hs.setAttribute("u", u);

				Cookie ck1 = new Cookie("un", u.getUsername());
				Cookie ck2 = new Cookie("pwd", u.getPassword());

				ck1.setMaxAge(30);
				ck2.setMaxAge(30);

				resp.addCookie(ck1);
				resp.addCookie(ck2);

				hs.setAttribute("msg", "Login Successfully!!!");
				hs.setAttribute("pagename", "home.jsp");
				hs.setAttribute("type", "success");
				resp.sendRedirect("popup.jsp");

				/*
				 * out.println("" + "<script>" + "alert('Welcome "+u.getUsername()+"');"
				 * +"window.location='home.jsp';" + "</script>");
				 */

				/*
				 * out.
				 * println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js</script>"
				 * ); out.
				 * println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"
				 * ); out.
				 * println("<script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>"
				 * ); out.println("<script >"); out.println("$(document).ready(function(){");
				 * out.println("alert('welcome'"+u.getUsername()+");");
				 * 
				 * out.println("});"); out.println("</script>");
				 */

			} else {
				hs.setAttribute("msg", "Invalid Password or Username!!!");
				hs.setAttribute("pagename", "login.jsp");
				hs.setAttribute("type", "error");
				resp.sendRedirect("popup.jsp");

				/*
				 * out.println("" + "<script>" + "alert('Incorrect username or password');" +
				 * "window.location='login.jsp';" + "</script>");
				 */

				/*
				 * out.println("<script >"); out.println("$(document).ready(function(){");
				 * out.println("alert('Invalid login');");
				 * 
				 * out.println("});"); out.println("</script>");
				 */

			}
		} catch (Exception e) {
			PrintWriter out = resp.getWriter();
			out.println(e);
		}
	}
}