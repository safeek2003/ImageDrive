import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
/**
 * Servlet implementation class Image
 */
@WebServlet("/Image")
@MultipartConfig
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final Part filePart = request.getPart("photo");  
		String imgname=request.getParameter("name");
	    String filename=filePart.getSubmittedFileName();
	    String [] arr=filename.split("\\.");
	    String imgtype=arr[arr.length-1];
	    System.out.println(imgtype);

	    OutputStream otpStream=null;
    	InputStream iptStream=null;
	    try {  
            String id="";
			
			Cookie ck[]=request.getCookies();  
		
			for(Cookie cookie : ck){
			    if("user".equals( cookie.getName())){
			        id=cookie.getValue();
			    }
			    }
	    	String path="http://localhost:8080/ImgAddFetch/pics/"+imgname+"."+imgtype;
			AddpathDb add=new AddpathDb();
			String state=add.storepath(id,path,imgname);
	      //http://localhost:8081/ImgAddFetch/pics/venkat.png
			if(state.equals("Added Successfully")) {
				
				
            otpStream = new FileOutputStream(new File("/home/safeek-zstk256/eclipse-workspace/ImgAddFetch/src/main/webapp/pics/" + File.separator +imgname+"."+imgtype));
	        iptStream =filePart.getInputStream();  
	        int read = 0;  
	          
	        final byte[] bytes = new byte[1024];  
	          
	        while ((read = iptStream.read(bytes)) != -1) {  
	            otpStream.write(bytes, 0, read);  
	        }  
	        
	        

	        
	       // http://localhost:8080/ImgAddFetch/pics/zmail.png"
			
	        PrintWriter writer =new PrintWriter(otpStream);
	        writer.println("New file " + filename + " created");  
	        }
	        else {
	        	PrintWriter writer =new PrintWriter(otpStream);
	        	writer.println(state);
	        	System.out.println(state);
	        }
	    }
	        catch (Exception e){ 
	    	e.printStackTrace(); 
            }
	        finally {
	  
	     }
	  
	}

}
