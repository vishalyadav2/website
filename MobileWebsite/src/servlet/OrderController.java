package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.instamojo.wrapper.api.ApiContext;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderResponse;

import model.user;

@WebServlet("/OrderController")
public class OrderController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int phoneid=Integer.parseInt(req.getParameter("phoneid"));
		
		 try 
	       {
			   Class.forName("com.mysql.jdbc.Driver");
			   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moobile","root","");
			   PreparedStatement ps=con.prepareStatement("select * from phone where phoneid=?");
			   ps.setInt(1, phoneid);
			   ResultSet rs=ps.executeQuery();
			   
			   String name="";
			   float price=0.0f;
			   
			   if(rs.next()) {
				   name=rs.getString("phonename");
				   price=rs.getFloat("price");
			   }
			   
			   HttpSession hs=req.getSession();
			   user u=(user)hs.getAttribute("u");
			   
	           ApiContext context = ApiContext.create("test_Mopk8AvarnHXLiHirLRcAdEfYWu3YdoHHxv", "test_fd3pmi4MO4iVXdCWzqoWgGgBXozK7wAXWUlTyoLLaSXXzwodQdCy3CXz1IP9QM2sBIxTK74yXPD7rCohVfGdIUGMZEBb5BXEhFbmqM0P4acy5dvp7QejrZAYYIB", ApiContext.Mode.TEST);
	           Instamojo api = new InstamojoImpl(context);

	           PaymentOrder order = new PaymentOrder();
	           order.setName(u.getUsername());
	           order.setEmail(u.getEmail());
	           order.setPhone("9819069833");
	           order.setCurrency("INR");
	           order.setAmount((double)price);
	           order.setDescription("Description");
	           order.setRedirectUrl("https://www.google.com/");
	           order.setWebhookUrl("https://www.google.com/");
	           order.setTransactionId(UUID.randomUUID().toString());

	           PaymentOrderResponse paymentOrderResponse = api.createPaymentOrder(order);
	           resp.sendRedirect(paymentOrderResponse.getPaymentOptions().getPaymentUrl());
	       }
	       catch (Exception e) 
	       {
	           System.out.println(e);
	       }
	}
}
