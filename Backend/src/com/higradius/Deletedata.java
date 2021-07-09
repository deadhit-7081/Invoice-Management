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
import com.higradius.model.DeleteDataModal;

/**
 * Servlet implementation class Deletedata
 */
@WebServlet("/deleteData")
public class Deletedata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deletedata() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
	    DeleteDataModal deleteData = gson.fromJson(demo,DeleteDataModal.class);
	    
	    String arr[] = deleteData.getInvoiceNumber();
	    
	    ////System.out.println(arr[0]);
	    
	    ServletContext ctx = getServletContext();
		String url = ctx.getInitParameter("Url");
		String username = ctx.getInitParameter("Username");
		String password = ctx.getInitParameter("Password");
		
		String query = "delete from invoice where invoice_id=?";
		int count=0;
	    
	    try
	    {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			PreparedStatement pst = con.prepareStatement(query);
			
			for(String s:arr)
			{
				pst.setString(1,s);
				pst.executeUpdate();
				count+=1;
			}
			
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
