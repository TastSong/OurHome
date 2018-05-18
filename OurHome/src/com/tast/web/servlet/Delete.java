package com.tast.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				Class.forName("com.mysql.jdbc.Driver");
				String area = request.getParameter("area");
				String place = request.getParameter("place");			
				String time = request.getParameter("time");
				//System.out.println("area = " + area + " place = " + place + " time = " + time);
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/ourhome", "root", "1647283556");
				stm = con.createStatement();
				 if(!con.isClosed())
				 {
					 System.out.println("connecting to the database successfully!");
				 }			 
				//获取记录主键
				String sql = "select * from t_pm25 "+" where f_area = '"+area+"'" + " and f_place = '"+place+"'" +" and f_time = '"+time+"';";
				rs = stm.executeQuery(sql); 
				int id = 0;
				if (rs != null) {
					while(rs.next())
					{
						id = rs.getInt(1);
					}
				}
				//删除记录
				sql = "delete from t_pm25 "+" where f_id = '"+id+"';";	
				stm.executeUpdate(sql); 
				
				rs.close(); 
				stm.close();
				con.close(); 			    
				  
		 } catch(ClassNotFoundException e) 
		  {  
			  System.out.println("sorry, can't find the driver!");  
			  e.printStackTrace(); 
			  
		  } catch(SQLException e)
		  {
			  e.printStackTrace();
			  
		  } catch(Exception e)
		  {
			  e.printStackTrace();
			  
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
