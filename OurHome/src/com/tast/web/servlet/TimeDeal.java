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

import com.google.gson.JsonArray;

/**
 * Servlet implementation class TimeDeal
 */
@WebServlet("/TimeDeal")
public class TimeDeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeDeal() {
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
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ourhome", "root", "1647283556");
			stm = con.createStatement();
			String sql = "create or  replace \r\n" + 
					"view date(t_year,t_month,t_day,t_hour)\r\n" + 
					"as\r\n" + 
					"select distinct substring(f_time , 1,4) , substring(f_time , 6,2),substring(f_time , 9,2), substring(f_time , 12,8)\r\n" + 
					"from t_pm25;\r\n";
			
			String sql2 = "SELECT DISTINCT t_year FROM date";
			stm.executeUpdate(sql);
			if(year != null && !"".equals(year))
			{
				if (month != null && !"".equals(month))
				{
					if (day != null && !"".equals(day))
					{
						sql2 = "SELECT DISTINCT t_hour FROM date where t_year='"+year+"' and t_month='"+month+"' and t_day='"+day+"' order by t_hour";
					}
					else
					{
						sql2 = "SELECT DISTINCT t_day FROM date where t_year='"+year+"' and t_month='"+month+"' order by t_day";
					}
				}
				else
				{
					sql2 = "SELECT DISTINCT t_month FROM date where t_year='"+year+"' order by t_month";
					System.out.println(sql2);
				}
			}
			rs = stm.executeQuery(sql2); 
			System.out.println(sql2);
			JsonArray array = new JsonArray();
			
			
			if (rs != null) {
				while(rs.next())
				{
					String 	a = rs.getString(1);
					
					
					
					array.add(a);
					
				}
				
				//JsonObject JsonObj = new JsonObject();
				//JsonObj.add("data", array);
				
				 PrintWriter out=response.getWriter();       //向客户端发送字符数据
				 response.setContentType("application/json; charset=UTF-8"); 
			     //System.out.println(.toString());
				 out.write(array.toString());                     //将str字符传输到前台    
				 out.flush();
				}
			
			}
			catch(SQLException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e)
            {
            	e.printStackTrace();
            }finally {
            	try {
            	rs.close();
            	stm.close();
            	con.close();
            	}catch(SQLException e) {
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
