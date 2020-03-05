package com.cognizant;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.database.DbConnection;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		java.sql.Connection con=null;
		java.sql.PreparedStatement pstmt=null;
		String username=request.getParameter("uname");
		String address=request.getParameter("address");
		String password=request.getParameter("pwd");
		
		//String query="insert into Detail values(?,?,?)";
		try{
			con=DbConnection.getConnection();
		pstmt=con.prepareStatement("insert into try1(username,password,Address) values(?,?,?)");
		pstmt.setString(1, username);
		pstmt.setString(2,password);
		pstmt.setString(3, address);
		//pstmt.setString(4, address);
		
		int i=pstmt.executeUpdate();	
		if(i!=0){
		//out.println("running");
		RequestDispatcher rd=request.getRequestDispatcher("Success.jsp");  
        rd.include(request, response);
		}else{
			out.println("not running");
			RequestDispatcher rd= request.getRequestDispatcher("Fail.jsp");
			rd.forward(request, response);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				pstmt.close();
				con.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
