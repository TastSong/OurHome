package com.tast.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig; 
import javax.servlet.ServletException; 
import javax.servlet.ServletRequest; 
import javax.servlet.ServletResponse; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
 
public class LoginServlet<JSONArray> implements javax.servlet.Servlet
{ 
	 public void doPost(HttpServletRequest request,HttpServletResponse response)
	 throws ServletException,IOException
	 {
		 String userName = request.getParameter("username");//ȡ���û���
		 String password = request.getParameter("password");//ȡ������
		 //response.sendRedirect("index.jsp");
		 if(UserLogin(userName, password))
		 {
			 request.getRequestDispatcher("/WEB-INF/index.html").forward(request,response);
			 request.getRequestDispatcher("/WEB-INF/index.html").forward(request,response);
		 }
		 else
		 {
			 response.sendRedirect("LoginFailure.html"); 
		 }
     }
	 
	 public boolean UserLogin(String userName, String password)
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
			 String sql = "select username, password from login";

			 ResultSet rs = statement.executeQuery(sql);  

			  // ���student��������Ϣ
			  while(rs.next()) 
			  {
				   //System.out.println(rs.getString("username") + "\t" + rs.getString("password")); 
				   if(userName.equals(rs.getString("username")) )
				   {
					   if(password.equals( rs.getString("password")))
					   {
						   System.out.println("��½�ɹ�");
						   return true;
					   }
				   }
			   } 

			  rs.close(); 
			  conn.close(); 
			  conn.close();  
			  
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

		  return false;
	 }
 
	 public void destroy()
	 { 
		 
	 }
	 
	 public ServletConfig getServletConfig()
	 {
		 return null;
	 }
	 
	 public String getServletInfo() 
	 {
		 return null;
	 }
	 
	 public void init(ServletConfig arg0) throws ServletException
	 {  
		 
	 }
	 
	 public void service(ServletRequest request, ServletResponse response)
	   throws ServletException, IOException 
	 {
		  HttpServletRequest rq = (HttpServletRequest)request;
		  HttpServletResponse rs = (HttpServletResponse) response;
		  doPost(rq,rs);
	 }  
}