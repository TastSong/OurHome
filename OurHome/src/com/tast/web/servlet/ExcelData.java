package com.tast.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class ExcelData
 */
@WebServlet("/ExcelData")
public class ExcelData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcelData() {
        super();
        // TODO Auto-generated constructor stub
    }
    private String Deadline(String startTime, String h) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = format.parse(startTime);
		Calendar c = Calendar.getInstance();    
	    c.setTime(d);   //设置时间  
	    c.add(Calendar.HOUR, Integer.parseInt(h)); //日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)    
	    Date deadline = c.getTime(); //结果  
		return format.format(deadline); 
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
			String deadlineH = request.getParameter("deadline");
			String major = request.getParameter("major");
			System.out.println(deadlineH);
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ourhome", "root", "1647283556");
			stm = con.createStatement();
			String sql = "SELECT distinct * FROM t_pm25 ";
			System.out.println(time);
			String time2 = Deadline(time, deadlineH);
			
			if (area != null && !area.equals(Jude))
			{
				sql = sql + "WHERE f_area='"+area+"'";
				if(place != null && !place.equals(Jude))
					sql = sql + " AND f_place='"+place+"'";
				if(pollutant != null && !pollutant.equals(Jude))
					sql = sql + " AND f_AQItype='"+pollutant+"'";
				if (time != null && !time.equals("年-月-日-时"))
					sql = sql + " AND f_time>'"+time+"' AND f_time<'"+time2+"'";
				if(major != null && !major.equals(Jude))
					sql = sql + " AND f_majorpollutant='"+major+"'";
			}
			else if(place != null && !place.equals(Jude))
			{
				sql = sql + " WHERE f_place='"+place+"'";
				if(pollutant != null && !pollutant.equals(Jude))
					sql = sql + " AND f_AQItype='"+pollutant+"'";
				if (time != null && !time.equals("年-月-日-时"))
					sql = sql + " AND f_time>'"+time+"' AND f_time<'"+time2+"'";
				if(major != null && !major.equals(Jude))
					sql = sql + " AND f_majorpollutant='"+major+"'";
			}
			else if(pollutant != null && !pollutant.equals(Jude))
			{
				sql = sql + "WHERE f_AQItype='"+pollutant+"'";
				if (time != null && !time.equals("年-月-日-时"))
					sql = sql + " AND f_time>'"+time+"' AND f_time<'"+time2+"'";
				if(major != null && !major.equals(Jude))
					sql = sql + " AND f_majorpollutant='"+major+"'";
			}
			else if (!time.equals("年-月-日-时") && time != null )
			{
				//System.out.println( Integer.parseInt(deadlineH));
				sql = sql + " AND f_time>'"+time+"' AND f_time<'"+time2+"'";
				if(major != null && !major.equals(Jude))
					sql = sql + " AND f_majorpollutant='"+major+"'";
			}
			else if (!time.equals("年-月-日-时") && time != null )
			{
				
				
				sql = sql + " AND f_time>'"+time+"' AND f_time<'"+time2+"'";
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
            } catch (ParseException e) {
				// TODO Auto-generated catch block
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
