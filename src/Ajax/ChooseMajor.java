package Ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MajorDAO;

/**
 * Servlet implementation class ChooseMajor
 */
@WebServlet("/ChooseMajor")
public class ChooseMajor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String univ = request.getParameter("univ");
		if(univ != null) {
			ArrayList<String> majorList = new MajorDAO().getMajorList(univ);
			response.getWriter().print("<select name='userMajor'>");
			for(int i=0; i<majorList.size(); i++)
				response.getWriter().print("<option value='" +majorList.get(i)+ "'>"+majorList.get(i)+"</option>");
			response.getWriter().print("</select>");
		}
	}
}
