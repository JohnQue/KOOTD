package Ajax;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class ImageUploadServlet
 */
@WebServlet("/ImageUploadServlet")
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath = "C:/javalec/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/LastTeamProject/upload";
		int size = 100 * 1024 * 1024;
		String encoding="UTF-8";
		String fileName = "";
		
		try{
	        // 파일업로드 및 업로드 후 파일명 가져옴
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, encoding, new DefaultFileRenamePolicy());
			@SuppressWarnings("rawtypes")
			Enumeration files = multi.getFileNames();
			String file = (String)files.nextElement(); 
			fileName = multi.getFilesystemName(file); 
		}catch(Exception e){
			e.printStackTrace();
		}
	    // 업로드된 경로와 파일명을 통해 이미지의 경로를 생성
		uploadPath = new String("http://localhost:8080/LastTeamProject/upload/"+fileName);
		
		
		JSONObject jobj = new JSONObject();
		jobj.put("url", uploadPath);

		response.setContentType("application/json");
		response.getWriter().print(jobj.toString());
	}

}
