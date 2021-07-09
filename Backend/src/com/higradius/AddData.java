package com.higradius;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.digester.SystemPropertySource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.higradius.model.AddDataModal;

/**
 * Servlet implementation class AddData
 */
@WebServlet("/addData")
public class AddData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
		response.setContentType("application/json");
		
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
	    //System.out.println("Demo - "+demo);
	    
	    Gson gson = new Gson();
	    AddDataModal addData = gson.fromJson(demo, AddDataModal.class);
	    
	    String cname = addData.getCustomerName();
	    String cnum = addData.getCustomerNumber();
	    String invonum = addData.getInvoiceNumber();
	    String invoAmt = addData.getInvoiceAmount();
	    String dueinDate = addData.getDueinDate();
	    String notes = addData.getNote();
	    
	    long miliseconds = System.currentTimeMillis();
        Date postingDate = new Date(miliseconds);
        
        
        Double invoAmount = Double.parseDouble(invoAmt);
	    
	    //System.out.println(cname+" "+cnum+" "+invonum+" "+invoAmount+" "+dueinDate+" "+notes+" "+postingDate);
	    
	    Date due = Date.valueOf(dueinDate);
		
	    
	    //System.out.println(cname+" "+cnum+" "+invonum+" "+invoAmount+" "+due+" "+notes+" "+postingDate);
	    
	    ServletContext ctx = getServletContext();
		String url = ctx.getInitParameter("Url");
		String username = ctx.getInitParameter("Username");
		String password = ctx.getInitParameter("Password");
		
		String query = "insert into invoice(cust_number,name_customer,due_in_date,total_open_amount,invoice_id,notes,posting_date)values(?,?,?,?,?,?,?)";                                                                              
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			PreparedStatement pst = con.prepareStatement(query); 
			
			pst.setString(1,cnum);
			pst.setString(2,cname);
			pst.setDate(3,due);
			pst.setDouble(4,invoAmount);
			pst.setString(5,invonum);
			pst.setString(6,notes);
			pst.setDate(7,postingDate);
			
			int count = pst.executeUpdate();
			
			//System.out.println(count+" rows affected");
			response.getWriter().write("Row inserted - "+count);
			pst.close();
			con.close();
			
		}
		catch(Exception e)
		{
			response.addHeader("duplicate","true");
			response.addHeader("data","The user with this invoice number already exists");
			System.out.println(e);
		}
	    
	    	    
	    
		doGet(request, response);
	}

}
