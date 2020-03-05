package com.cognizant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.database.DbConnection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;
		PreparedStatement pstmt=null;
		Statement st=null;
		HttpSession session=request.getSession();
			String id=null;
			String name=null;
			String password=null;
			id=request.getParameter("id");
			name=request.getParameter("uname");
			password=request.getParameter("pwd");
			int flag=0;
			session.setAttribute("pas", password);
			try{
				con=DbConnection.getConnection();
				
				st=con.createStatement();
				
				ResultSet rs=st.executeQuery("select *from try1");
				System.out.println("id\tname\tpassword");
				
				
				while(rs.next()){
					if(rs.getString(2).equalsIgnoreCase(name)&&(rs.getString(3).equals(password))){
						flag=1;
						break;
					}
					
					
				}if(flag==1){
					javax.servlet.RequestDispatcher rd=request.getRequestDispatcher("Success.jsp");  
			        rd.forward(request, response);
				}
				else{
					

					javax.servlet.RequestDispatcher rd= request.getRequestDispatcher("Fail.jsp");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
