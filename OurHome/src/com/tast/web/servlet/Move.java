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
 * Servlet implementation class Move
 */
@WebServlet("/Move")
public class Move extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Move() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
						String label = request.getParameter("label");
						//String major = request.getParameter("major");
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
						//修改记录
						System.out.println("label = " + label);
						if(label.equals("Table1"))
						{
							String major = request.getParameter("major");
							sql = "update t_pm25 set f_majorpollutant = '"+major+"'"+" where f_id = '"+id+"';";	
							System.out.println("major = " + major);
							System.out.println(sql);
							stm.executeUpdate(sql); 
						}
						if(label.equals("Table2"))
						{
							String pollution = request.getParameter("pollution");
							String strPollutionIndex = request.getParameter("pollutionIndex");
							int pollutionIndex = Integer.parseInt(strPollutionIndex);
							sql = "update t_pm25 set "+pollution+" = '"+pollutionIndex+"'"+" where f_id = '"+id+"';";	
							System.out.println(sql);
							stm.executeUpdate(sql); 
						}
						if(label.equals("Table3"))
						{
							String strAQI = request.getParameter("aQI");
							int aQI = Integer.parseInt(strAQI);
							sql = "update t_pm25 set f_AQI = '"+aQI+"'"+" where f_id = '"+id+"';";	
							System.out.println(sql);
							stm.executeUpdate(sql); 
                            String f_AQItype = AQIGrade(aQI);
							sql = "update t_pm25 set f_AQItype = '"+f_AQItype+"'"+" where f_id = '"+id+"';";	
							System.out.println(sql);
							stm.executeUpdate(sql); 
						}
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

	public String AQIGrade(int aQI)
	{
		if(aQI >= 51 && aQI < 101)
		{
			return "良";
		}
		else if(aQI >= 101 && aQI < 151)
		{
			return "轻度污染";
		}
		else if(aQI >= 151 && aQI < 201)
		{
			return "中度污染";
		}
		else if(aQI >= 201 && aQI < 301)
		{
			return "重度污染";
		}
		else if(aQI >= 301 && aQI < 501)
		{
			return "严重污染";
		}
		else
		{
			return "爆表";
		}
	}
}










