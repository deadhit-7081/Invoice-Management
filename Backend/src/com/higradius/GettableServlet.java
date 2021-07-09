package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.higradius.model.Invoice;

/**
 * Servlet implementation class GettableServlet
 */
@WebServlet("/")
public class GettableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GettableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
		PrintWriter out = response.getWriter();
		ServletContext ctx = getServletContext();
		String url = ctx.getInitParameter("Url");
		String username = ctx.getInitParameter("Username");
		String password = ctx.getInitParameter("Password");
		
		//String query = "select * from invoice order by posting_date desc limit 20";
		String query = "select name_customer,cust_number,invoice_id,total_open_amount,due_in_date,predicted_date,notes from invoice ORDER BY posting_date desc limit 1000";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				Invoice invo = new Invoice();
				
				invo.setName_customer(rs.getString(1));
				invo.setCust_name(rs.getString(2));
				invo.setInvoice_id(rs.getDouble(3));
				invo.setTotal_open_amount(rs.getDouble(4));
				invo.setDue_in_date(rs.getDate(5));
				invo.setPredicted_date(rs.getDate(6));
				invo.setNotes(rs.getString(7));
				invoices.add(invo);
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
		
		String json = new Gson().toJson(invoices);
		response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
		response.setContentType("application/json");
		////System.out.println(json);
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
