package com.tast.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class Prediction
 */
@WebServlet("/Prediction")
public class Prediction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prediction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String area = request.getParameter("area");
		JsonArray array = new JsonArray();
		JsonObject subJsonObj = new JsonObject();
		if(area.equals("滨州市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 98);
			subJsonObj.addProperty("2014-11-20 8:00:00", 181);
			subJsonObj.addProperty("2014-11-20 9:00:00", 127);			
		}
		if(area.equals("德州市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 179);
			subJsonObj.addProperty("2014-11-20 8:00:00", 248);
			subJsonObj.addProperty("2014-11-20 9:00:00", 225);			
		}
		if(area.equals("东营市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 143);
			subJsonObj.addProperty("2014-11-20 8:00:00", 207);
			subJsonObj.addProperty("2014-11-20 9:00:00", 168);			
		}
		if(area.equals("菏泽市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 221);
			subJsonObj.addProperty("2014-11-20 8:00:00", 237);
			subJsonObj.addProperty("2014-11-20 9:00:00", 256);			
		}
		if(area.equals("济南市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 139);
			subJsonObj.addProperty("2014-11-20 8:00:00", 139);
			subJsonObj.addProperty("2014-11-20 9:00:00", 156);			
		}
		if(area.equals("济宁市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 192);
			subJsonObj.addProperty("2014-11-20 8:00:00", 161);
			subJsonObj.addProperty("2014-11-20 9:00:00", 127);			
		}
		if(area.equals("莱芜市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 0);
			subJsonObj.addProperty("2014-11-20 8:00:00", 229);
			subJsonObj.addProperty("2014-11-20 9:00:00", 56);			
		}
		if(area.equals("聊城市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 225);
			subJsonObj.addProperty("2014-11-20 8:00:00", 231);
			subJsonObj.addProperty("2014-11-20 9:00:00", 243);			
		}
		if(area.equals("临沂市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 204);
			subJsonObj.addProperty("2014-11-20 8:00:00", 275);
			subJsonObj.addProperty("2014-11-20 9:00:00", 165);			
		}
		if(area.equals("青岛市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 62);
			subJsonObj.addProperty("2014-11-20 8:00:00", 96);
			subJsonObj.addProperty("2014-11-20 9:00:00", 81);			
		}
		if(area.equals("日照市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 135);
			subJsonObj.addProperty("2014-11-20 8:00:00", 158);
			subJsonObj.addProperty("2014-11-20 9:00:00", 184);			
		}
		if(area.equals("泰安市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 146);
			subJsonObj.addProperty("2014-11-20 8:00:00", 161);
			subJsonObj.addProperty("2014-11-20 9:00:00", 154);			
		}
		if(area.equals("潍坊市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 100);
			subJsonObj.addProperty("2014-11-20 8:00:00", 182);
			subJsonObj.addProperty("2014-11-20 9:00:00", 174);			
		}
		if(area.equals("威海市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 78);
			subJsonObj.addProperty("2014-11-20 8:00:00", 51);
			subJsonObj.addProperty("2014-11-20 9:00:00", 69);			
		}
		if(area.equals("烟台市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 88);
			subJsonObj.addProperty("2014-11-20 8:00:00", 85);
			subJsonObj.addProperty("2014-11-20 9:00:00", 83);			
		}
		if(area.equals("枣庄市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 210);
			subJsonObj.addProperty("2014-11-20 8:00:00", 177);
			subJsonObj.addProperty("2014-11-20 9:00:00", 183);			
		}
		if(area.equals("淄博市"))
		{			
			subJsonObj.addProperty("2014-11-15 12:00:00", 136);
			subJsonObj.addProperty("2014-11-20 8:00:00", 148);
			subJsonObj.addProperty("2014-11-20 9:00:00", 169);			
		}
		
		array.add(subJsonObj);
		PrintWriter out = response.getWriter();       //向客户端发送字符数据
		response.setContentType("application/json; charset=UTF-8"); 
	    out.write(array.toString());                     //将str字符传输到前台    
		out.flush();
		//System.out.println("数据传输完成");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
