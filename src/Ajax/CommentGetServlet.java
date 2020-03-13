package Ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;
import DTO.CommentDTO;

/**
 * Servlet implementation class CommentGetServlet
 */
@WebServlet("/CommentGetServlet")
public class CommentGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int commentNum = Integer.parseInt(request.getParameter("commentNum"));
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		CommentDAO dao = CommentDAO.getInstance();
		CommentDTO dto = dao.getComment(commentNum);
		response.getWriter().write("<form action = 'modifyComment.do' method='post'>");
		response.getWriter().write("<input type='hidden' name='commentNum' value='"+commentNum+"'>");
		response.getWriter().write("<input type='hidden' name='boardNum' value='"+boardNum+"'>");
		response.getWriter().write("<input type='text' name='userComment' value='"+dto.getUserComment()+"' onkeydown='if(event.keyCode==13) {return false;}'>");
		response.getWriter().write("<input id='sb' type='submit' value='수정하기'>");
		response.getWriter().write("</form>");
		//		response.getWriter().write(getJSON(commentNum));
	}
	//	public String getJSON(int commentNum) {
	//		StringBuffer result = new StringBuffer("");
	//		result.append("{\"result\":");
	//		CommentDAO dao = CommentDAO.getInstance();
	//		CommentDTO dto = dao.getComment(commentNum);
	//		result.append("[{\"value\": \"" +dto.getUserComment()+ "\"}]}");
	//		return result.toString();
	//	}
}
