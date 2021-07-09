package com.higradius;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.higradius.model.EditDataModal;

/**
 * Servlet implementation class Editdata
 */
@WebServlet("/editData")
public class Editdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Editdata() {
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
	    //System.out.println("Demo Edit - "+demo);
	    
	    Gson gson = new Gson();
	    EditDataModal editData = gson.fromJson(demo,EditDataModal.class);
	    
	    String invonum = editData.getInvoiceNumber();
	    String invoamt = editData.getInvoiceAmount();
	    String notes = editData.getNote();
	    
	    Double invoAmount = Double.parseDouble(invoamt);
	    
	    ServletContext ctx = getServletContext();
		String url = ctx.getInitParameter("Url");
		String username = ctx.getInitParameter("Username");
		String password = ctx.getInitParameter("Password");
		
		String query = "Update invoice set total_open_amount=?,notes=? where invoice_id="+invonum;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setDouble(1,invoAmount);
			pst.setString(2,notes);
			
			int count = pst.executeUpdate();
			
			//System.out.println(count+" rows affected");
			response.getWriter().write("Row inserted - "+count);
			pst.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	    
	    
		
		doGet(request, response);
	}

}
