
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

      File uploadedFile = new File("F:\\projects\\AppAnalyzer\\AppAnalyzer\\apk\\" + fileName);
      String filePath = uploadedFile.getAbsolutePath();
      System.out.println(filePath);
      FileOutputStream os = new FileOutputStream(uploadedFile);
      os.write(b);
      os.close();
      System.out.println("Save the uploaded file under "+filePath);
      is.close();
    }

    if(fileName != null){
    	System.out.println("Start decompiling...");
    	AndroGuard ag = new AndroGuard(fileName);
    	System.out.println("Byte Code file is under "+ag.decompile());
    }
    
    response.sendRedirect("results.jsp");
  }

  private String getFileName(Part part) {
    String partHeader = part.getHeader("content-disposition");
    for (String cd : partHeader.split(";")) {
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
