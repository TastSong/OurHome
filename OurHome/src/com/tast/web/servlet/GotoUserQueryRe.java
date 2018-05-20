package com.tast.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GotoUserQueryRe
 */
@WebServlet("/GotoUserQueryRe")
public class GotoUserQueryRe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GotoUserQueryRe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("tiaozhuanb");
		request.getRequestDispatcher("/WEB-INF/UserQuery.html").forward(request,response);
		String username = UserRegister.GetUserName();
		String password = UserRegister.GetPassword();
		InsertUser(username, password);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	 public void InsertUser(String userName, String password)
	 {
		 //����������
		  String driver = "com.mysql.jdbc.Driver";
		  // ���ݱ�url
		  String url = "jdbc:mysql://localhost:3306/myhome";
		  // MySQL�û���
		  String user = "root";
		  // MySQL����
		  String passwordSQL = "1647283556";

		  // ��ʼ�������ݿ�
		  try 
		  {
			 // ������������
			 Class.forName(driver);
			 // �������ݿ�
			 Connection conn = DriverManager.getConnection(url, user, passwordSQL);
			 if(!conn.isClosed())
			 {
				 System.out.println("connecting to the database successfully!");
			 }
			 
			 // statement����ִ��SQL���
			 Statement statement = conn.createStatement();
			 // select���
			 String sql = "insert into login values("+"'"+userName+"',"+"'"+password+"')";
			//String sql = "insert into user values('abc111','1234');";
		    statement.executeUpdate(sql);
			//System.out.println(a);				   
			conn.close(); 
			statement.close();  
			  
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
}
