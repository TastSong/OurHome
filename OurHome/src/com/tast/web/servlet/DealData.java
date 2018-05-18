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
import com.google.gson.JsonObject;

/**
 * Servlet implementation class DealData
 */
@WebServlet("/DealData")
public class DealData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DealData() {
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
			String Jude = "请选择";
			String area = request.getParameter("area");
			String place = request.getParameter("place");
			String pollutant = request.getParameter("quality");
			String time = request.getParameter("time");
			String major = request.getParameter("major");
			//System.out.println(major);
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ourhome", "root", "1647283556");
			stm = con.createStatement();
			String sql = "SELECT * FROM t_pm25 ";

			
			if (area != null && !area.equals(Jude))
			{
				sql = sql + "WHERE f_area='"+area+"'";
				if(place != null && !place.equals(Jude))
					sql = sql + " AND f_place='"+place+"'";
				if(pollutant != null && !pollutant.equals(Jude))
					sql = sql + " AND f_AQItype='"+pollutant+"'";
				if (time != null && !time.equals(":00:00"))
					sql = sql + " AND f_time='"+time+"'";
				if(major != null && !major.equals(Jude))
					sql = sql + " AND f_majorpollutant='"+major+"'";
			}
			else if(place != null && !place.equals(Jude))
			{
				sql = sql + " WHERE f_place='"+place+"'";
				if(pollutant != null && !pollutant.equals(Jude))
					sql = sql + " AND f_AQItype='"+pollutant+"'";
				if (time != null && !time.equals(":00:00"))
					sql = sql + " AND f_time='"+time+"'";
				if(major != null && !major.equals(Jude))
					sql = sql + " AND f_majorpollutant='"+major+"'";
			}
			else if(pollutant != null && !pollutant.equals(Jude))
			{
				sql = sql + "WHERE f_AQItype='"+pollutant+"'";
				if (time != null && !time.equals(":00:00"))
					sql = sql + " AND f_time='"+time+"'";
				if(major != null && !major.equals(Jude))
					sql = sql + " AND f_majorpollutant='"+major+"'";
			}
			else if (time != null && !time.equals(":00:00"))
			{
				sql = sql + "WHERE f_time='"+time+"'";
				if(major != null && !major.equals(Jude))
					sql = sql + " AND f_majorpollutant='"+major+"'";
			}
			else if(major != null && !major.equals(Jude))
				sql = sql + " WHERE f_majorpollutant='"+major+"'";
			System.out.println(sql);
			rs = stm.executeQuery(sql); 
			JsonArray array = new JsonArray();
			//int temp = 0;
			if (rs != null) {
				while(rs.next())
				{
					String a = rs.getString(3);
					String b = rs.getString(4);
					String c = rs.getString(5);
				    int d = rs.getInt(6);
					String e = rs.getString(7);
					int f = rs.getInt(8);
					int g = rs.getInt(9);
					int h = rs.getInt(10);
					int i = rs.getInt(11);
					int j = rs.getInt(12);
					int k = rs.getInt(13);
					int l = rs.getInt(14);
					String m = rs.getString(15);
										
					JsonObject subJsonObj1 = new JsonObject();
					subJsonObj1.addProperty("a", a);
					subJsonObj1.addProperty("b", b);
					subJsonObj1.addProperty("c", c);
					subJsonObj1.addProperty("d", d);
					subJsonObj1.addProperty("e", e);
					subJsonObj1.addProperty("f", f);
					subJsonObj1.addProperty("g", g);
					subJsonObj1.addProperty("h", h);
					subJsonObj1.addProperty("i", i);
					subJsonObj1.addProperty("j", j);
					subJsonObj1.addProperty("k", k);
					subJsonObj1.addProperty("l", l);
					subJsonObj1.addProperty("m", m);
					array.add(subJsonObj1);
					//temp++;
				}
				//object.add("data", array);
				
				
				 PrintWriter out=response.getWriter();       //向客户端发送字符数据
				 response.setContentType("application/json; charset=UTF-8"); 
			     System.out.println(array.toString());
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

