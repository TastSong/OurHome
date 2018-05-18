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
		 String userName = request.getParameter("username");//取得用户名
		 String password = request.getParameter("password");//取得密码
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
		 //驱动程序名
		  String driver = "com.mysql.jdbc.Driver";
		  // 数据表url
		  String url = "jdbc:mysql://localhost:3306/myhome";
		  // MySQL用户名
		  String user = "root";
		  // MySQL密码
		  String passwordSQL = "1647283556";
		  // 开始连接数据库
		  try 
		  {
			 // 加载驱动程序
			 Class.forName(driver);
			 // 连续数据库
			 Connection conn = DriverManager.getConnection(url, user, passwordSQL);
			 if(!conn.isClosed())
			 {
				 System.out.println("connecting to the database successfully!");
			 }
			 
			 // statement用来执行SQL语句
			 Statement statement = conn.createStatement();
			 // select语句
			 String sql = "select username, password from login";

			 ResultSet rs = statement.executeQuery(sql);  

			  // 输出student的所有信息
			  while(rs.next()) 
			  {
				   //System.out.println(rs.getString("username") + "\t" + rs.getString("password")); 
				   if(userName.equals(rs.getString("username")) )
				   {
					   if(password.equals( rs.getString("password")))
					   {
						   System.out.println("登陆成功");
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