

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FetchServ
 */
@WebServlet("/Fetch")
public class FetchServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id="";
		
		Cookie ck[]=request.getCookies();  
	
		for(Cookie cookie : ck){
		    if("user".equals( cookie.getName())){
		        id=cookie.getValue();
		    }
		    }
		PrintWriter pw=response.getWriter();
		 try {
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          try {
	          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Social", "root", "safeek2003");
	          PreparedStatement qe = con.prepareStatement("select name,path from imgpath where id=?");
	          qe.setString(1, id);
	          ResultSet rs=qe.executeQuery();
	          JSONArray arr = new JSONArray();  
	          while(rs.next()) {
	        	  String arr1[]=rs.getString(2).split("/");
	        	  JSONObject obj=new JSONObject();    
	        	  obj.put("name",rs.getString(1));    
	        	  obj.put("path",rs.getString(2));
	        	  
	        	  arr.add(obj);
	        	  
	          }
	            
	            pw.write(arr.toJSONString());
	            pw.flush();
	            pw.close();
	          }
	          catch(SQLException se) {
	         	 pw.write("Duplicate entry");
	          }
	 	 }
	 	 catch(Exception e) {
	 		 pw.write("Some thing wrong please try again");
	 	 }
	}

}
