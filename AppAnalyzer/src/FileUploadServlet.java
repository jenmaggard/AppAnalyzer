
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 
 * @author Jen
 *  This java class handles file upload
 */

@WebServlet(urlPatterns = "/fileUpload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

  public FileUploadServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
	  System.out.println("Calling to get the file");
	  String fileName = null;
    for (Part part : request.getParts()) {

      InputStream is = request.getPart(part.getName()).getInputStream();
      int i = is.available();
      byte[] b  = new byte[i];
      is.read(b);

      fileName = getFileName(part);
      System.out.println("File name : " + fileName);

      File uploadedFile = new File("/Users/Jen/git/AppAnalyzer/AppAnalyzer/apk/" + fileName);
      String filePath = uploadedFile.getName();
      System.out.println(filePath);
      FileOutputStream os = new FileOutputStream(uploadedFile);
      System.out.println(b);
      os.write(b);
      is.close();
    }

    if(fileName != null){
    	AndroGuard ag = new AndroGuard(fileName);
    	ag.decompile();
    }
    
    response.sendRedirect("results.jsp");
  }

  private String getFileName(Part part) {
    String partHeader = part.getHeader("content-disposition");
    for (String cd : part.getHeader("content-disposition").split(";")) {
      if (cd.trim().startsWith("filename")) {
        return cd.substring(cd.indexOf('=') + 1).trim()
            .replace("\"", "");
      }
    }
    return null;

  }

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
