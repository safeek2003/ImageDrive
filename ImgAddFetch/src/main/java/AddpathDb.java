import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddpathDb {
  static String storepath (String id,String path,String imgname) {
	  try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          try {
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Social", "root", "safeek2003");
          PreparedStatement qe = con.prepareStatement("insert into imgpath(id,path,name) values(?,?,?)");
          qe.setString(1, id);
          qe.setString(2, path);
          qe.setString(3,imgname);
          qe.executeUpdate();
          return "Added Successfully";
          }
          catch(SQLException se) {
         	 return "This img is already added";
          }
 	 }
 	 catch(Exception e) {
 		 return "Some thing wrong please try agan";
 	 }
	 
	     
  }
  
}
