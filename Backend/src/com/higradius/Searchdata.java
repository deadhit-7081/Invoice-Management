package com.higradius;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.higradius.model.Invoice;
import com.higradius.model.SearchDataModal;

/**
 * Servlet implementation class Searchdata
 */
@WebServlet("/searchData")
public class Searchdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Searchdata() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
		String demo="";
	    BufferedReader reader = request.getReader();
	    try {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            demo+=line;
	        }
	    } finally {
	        reader.close();
	    }
	    //System.out.println("Demo Edit Get - "+demo);
	    
	    Gson gson = new Gson();
	    SearchDataModal searchData = gson.fromJson(demo,SearchDataModal.class);
	    
	    String s = searchData.getInvoiceNumber();
	    
	    ServletContext ctx = getServletContext();
		String url = ctx.getInitParameter("Url");
		String username = ctx.getInitParameter("Username");
		String password = ctx.getInitParameter("Password");
	    
	    String query = "select * from invoice where invoice_id="+s;
	    
	    try
	    {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				Invoice invo = new Invoice();
				invo.setBusiness_code(rs.getString(1));
				invo.setCust_name(rs.getString(2));
				invo.setName_customer(rs.getString(3));
				invo.setClear_date(rs.getDate(4));
				invo.setBusiness_year(rs.getDouble(5));
				invo.setDoc_id(rs.getDouble(6));
				invo.setPosting_date(rs.getDate(7));
				invo.setDocument_create_date(rs.getDate(8));
				invo.setDocument_create_date_1(rs.getDate(9));
				invo.setDue_in_date(rs.getDate(10));
				invo.setInvoice_currency(rs.getString(11));
				invo.setDocument_type(rs.getString(12));
				invo.setPosting_id(rs.getDouble(13));
				invo.setTotal_open_amount(rs.getDouble(14));
				invo.setBaseline_create_date(rs.getDate(15));
				invo.setCust_payment_terms(rs.getString(16));
				invo.setInvoice_id(rs.getDouble(17));
				invo.setIsOpen(rs.getInt(18));
				invo.setPredicted_date(rs.getDate(19));
				invo.setAging_Bucket(rs.getString(20));
				invo.setNotes(rs.getString(21));
				invoices.add(invo);
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
	    catch(Exception e)
	    {
	    	System.out.println(e);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		doGet(request, response);
	}

}
