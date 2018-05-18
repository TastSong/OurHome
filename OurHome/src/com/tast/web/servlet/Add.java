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
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ�������
			String Jude = "��ѡ��";
			String f_area = request.getParameter("area");
			String f_time = request.getParameter("time");
			String f_place = request.getParameter("place");
			//String pollutant = request.getParameter("pollutant");
			String f_AQI = request.getParameter("AQI");
			//System.out.println("f_AQI = " + f_AQI );
			String f_AQItype = "";
			if(f_AQI != null && !"".equals(f_AQI))
			{
				f_AQItype = AQIGrade(Integer.parseInt(f_AQI));
			}
			else
			{
				f_AQItype = null;
			}
			//System.out.println("f_AQItype = " + f_AQItype);
			String f_PM10per1h = request.getParameter("PM10");
			String f_COper1h = request.getParameter("f_COper1h");
			String f_NO2per1h = request.getParameter("f_NO2per1h");
			String f_O3per1h = request.getParameter("f_O3per1h");
			String f_O3per8h = request.getParameter("f_O3per8h");
			String f_SO2per1h = request.getParameter("f_SO2per1h");
			String f_majorpollutant = request.getParameter("major");
			String f_PM25per1h = request.getParameter("PM25");
			if (f_time.equals(":00:00"))
				f_time = null;
			if (f_majorpollutant.equals(Jude))
				f_majorpollutant = null;
			
			System.out.println(f_PM25per1h);
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ourhome", "root", "1647283556");
			Statement stm = con.createStatement();
			//�����¼��ID
			String sql = "select max(f_id) from t_pm25;";
			ResultSet rs = stm.executeQuery(sql);
			int idMax = 0;
			if (rs != null) {
				while(rs.next())
				{
					idMax = rs.getInt(1);
				}
			}
			int id = idMax + 1;
			//System.out.println("id = " + id);
			//���AreaCode
			int f_areacode = IdentifyAreaCode(f_area);
			
			sql = "INSERT INTO t_pm25(f_id,f_areacode,f_area,f_time,f_place,f_AQI,f_AQItype,"
					+ "f_PM10per1h,f_COper1h,f_NO2per1h,f_O3per1h,f_O3per8h,f_SO2per1h,"
					+ "f_majorpollutant, f_PM25per1h) VALUE(" + id +"," + f_areacode +",'"
					+ f_area +"','" + f_time +"','" + f_place + "',";
			//*************************************
			if (f_AQI == null||"".equals(f_AQI))
				sql += "NULL";
			else
				sql += f_AQI;
			//*************************************
			sql += ",'" +f_AQItype+ "',";
			
			if (f_PM10per1h == null||"".equals(f_PM10per1h))
				sql += "NULL,";
			else
				sql += f_PM10per1h+",";
			//*************************************
			if (f_COper1h == null||"".equals(f_COper1h))
				sql += "NULL,";
			else
				sql += f_COper1h+",";
			//*************************************
			if (f_NO2per1h == null||"".equals(f_NO2per1h))
				sql += "NULL,";
			else
				sql += f_NO2per1h+",";
			//***********************************
			if (f_O3per1h == null||"".equals(f_O3per1h))
				sql += "NULL,";
			else
				sql += f_O3per1h+",";
			//***********************************
			if (f_O3per8h == null||"".equals(f_O3per8h))
				sql += "NULL,";
			else
				sql += f_O3per8h+",";
			//***********************************
			if (f_SO2per1h == null||"".equals(f_SO2per1h))
				sql += "NULL,";
			else
				sql += f_SO2per1h+",";
			
			sql += "'"+f_majorpollutant+"',";
			//***********************************
			if (f_PM25per1h == null||"".equals(f_PM25per1h))
				sql += "NULL)";
			else
				sql += f_PM25per1h+")";
			
			/*
			JudeString(f_PM10per1h)+"," + JudeString()+"," + JudeString()+"," + JudeString()+"," 
					+ JudeString()+"," + JudeString()+",'" + 
					+ "',"+JudeString()+")"
					;
					*/
			System.out.println(sql);
			stm.executeUpdate(sql);
			//System.out.println(area);
			
			rs.close(); 
			stm.close();
			con.close(); 
		
		}catch(SQLException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e)
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

	public int IdentifyAreaCode(String area)
	{
		if(area.equals("������"))
			return 370100;
		if(area.equals("�ൺ��"))
			return 370200;
		if(area.equals("�Ͳ���"))
			return 370300;
		if(area.equals("��ׯ��"))
			return 370400;
		if(area.equals("��Ӫ��"))
			return 370500;
		if(area.equals("��̨��"))
			return 370600;
		if(area.equals("Ϋ����"))
			return 370700;
		if(area.equals("������"))
			return 370800;
		if(area.equals("̩����"))
			return 370900;
		if(area.equals("������"))
			return 371000;
		if(area.equals("������"))
			return 371100;
		if(area.equals("������"))
			return 371200;
		if(area.equals("������"))
			return 371300;
		if(area.equals("������"))
			return 371400;
		if(area.equals("�ĳ���"))
			return 371500;
		if(area.equals("������"))
			return 371600;
		if(area.equals("������"))
			return 371700;
		
		return 0;
	}
	public String AQIGrade(int aQI)
	{
		if(aQI >= 51 && aQI < 101)
		{
			return "��";
		}
		else if(aQI >= 101 && aQI < 151)
		{
			return "�����Ⱦ";
		}
		else if(aQI >= 151 && aQI < 201)
		{
			return "�ж���Ⱦ";
		}
		else if(aQI >= 201 && aQI < 301)
		{
			return "�ض���Ⱦ";
		}
		else if(aQI >= 301 && aQI < 501)
		{
			return "������Ⱦ";
		}
		else
		{
			return "����";
		}
	}
}
