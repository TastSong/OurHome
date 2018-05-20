package com.tast.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.JsonObject;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	static String UserName;
	static String Password;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
        SetUser(username, password);
		
		if(Check(username,password) && !IsHavaUser(username, password))
		//if(true)	
		{//用户密码正确			
			JsonObject subJsonObj = new JsonObject();
			subJsonObj.addProperty("result", true);
			PrintWriter out = response.getWriter();       
		    response.setContentType("application/json; charset=UTF-8"); 
			out.write(subJsonObj.toString());                     
			out.flush();
		}
		else
		{
			//response.sendRedirect("LoginFailure.html"); //跳转失败页面
			JsonObject subJsonObj = new JsonObject();
			subJsonObj.addProperty("result", false);
			PrintWriter out = response.getWriter();       //向客户端发送字符数据
		    response.setContentType("application/json; charset=UTF-8"); 
			out.write(subJsonObj.toString());                     //将str字符传输到前台    
			out.flush();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	 public boolean IsHavaUser(String userName, String password)
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
		  Connection conn = null;
		  Statement statement = null;
		  ResultSet rs = null;
		  try 
		  {
			 // 加载驱动程序
			 Class.forName(driver);
			 // 连续数据库
			 conn = DriverManager.getConnection(url, user, passwordSQL);
			 if(!conn.isClosed())
			 {
				 System.out.println("connecting to the database successfully!");
			 }
			 
			 // statement用来执行SQL语句
			 statement = conn.createStatement();
			 // select语句
			 String sql = "select username, password from login";

			 rs = statement.executeQuery(sql);  

			  // 输出student的所有信息
			  while(rs.next()) 
			  {
				   //System.out.println(rs.getString("username") + "\t" + rs.getString("password")); 
				  String username = rs.getString("username");
				   if(username.equals(userName) )
				   {
						return true;
				   }
			   } 	
			   		  
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
			  
		  }finally {
          	try {
	          	rs.close(); //
	          	statement.close();
	          	conn.close();
          	}catch(SQLException e) {
          		e.printStackTrace();
          	}
          }

		  return false;
	 }
	 //检测注册信息是否合法
	 public boolean Check(String userName, String password)
	 {
		 if(userName == null || userName.length() <= 0)
		 {
			 return false;
		 }
		 if(password == null || password.length() <= 0)
		 {
			 return false;
		 }
		 return true;
	 }
	 private static void SetUser(String username, String password)
	 {
			UserName = username;
			Password = password;
	 }
	 public static String GetUserName()
	 {
		 return UserName;
	 }
	 public static String GetPassword() 
	 {
		 return Password;
	 }
}
