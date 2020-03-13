package Ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommentReplyServlet
 */
@WebServlet("/CommentReplyServlet")
public class CommentReplyServlet extends HttpServlet {
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
		response.getWriter().write("<div id='personal-comment'>");
		response.getWriter().write("<form action = 'replyComment.do' method='post'>");
		response.getWriter().write("<input type='hidden' name='commentNum' value='"+commentNum+"'>");
		response.getWriter().write("<input type='hidden' name='boardNum' value='"+boardNum+"'>");
		response.getWriter().write("<input type='text' name='userComment' onkeydown='if(event.keyCode==13) {return false;}'>");
		response.getWriter().write("<input id='sb' type='submit' value='답글달기'>");
		response.getWriter().write("</form></div>");
	}

}
