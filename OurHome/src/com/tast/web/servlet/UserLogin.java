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
 * Servlet implementation class UserLogin
 * @param <ONObject>
 */
@WebServlet("/UserLogin")
public class UserLogin<ONObject> extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
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
		
		if(UserLog(username, password))
		{//�û�������ȷ
			 JsonObject subJsonObj = new JsonObject();
			 subJsonObj.addProperty("result", true);
			 PrintWriter out = response.getWriter();       //��ͻ��˷����ַ�����
			 response.setContentType("application/json; charset=UTF-8"); 
			 out.write(subJsonObj.toString());                     //��str�ַ����䵽ǰ̨    
			 out.flush();			 
		}
		else
		{
			 //response.sendRedirect("LoginFailure.html"); //��תʧ��ҳ��
			 JsonObject subJsonObj = new JsonObject();
			 subJsonObj.addProperty("result", false);
			 PrintWriter out = response.getWriter();       //��ͻ��˷����ַ�����
			 response.setContentType("application/json; charset=UTF-8"); 
			 out.write(subJsonObj.toString());                     //��str�ַ����䵽ǰ̨    
			 out.flush();
		}
		

	}

	 public boolean UserLog(String userName, String password)
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}