package Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;

/* 게시판에 글 쓰기 */
public class BoardWriteCommand implements ACommand {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		BoardDAO dao = BoardDAO.getInstance();
		String boardKind = request.getParameter("boardKind");
		String boardUserID = request.getParameter("boardUserID");
		String boardUserName = request.getParameter("boardUserName");
		String boardUserMajor = request.getParameter("boardUserMajor");
		String boardTitle = request.getParameter("title");
		String boardContent = request.getParameter("content");
		String caps = request.getParameter("cap");
		String shirts = request.getParameter("shirts");
		String jackets = request.getParameter("jacket");
		String pants = request.getParameter("pants");
		String shoes = request.getParameter("shoes");
		String bag = request.getParameter("bag");
		String acce = request.getParameter("acce");
		if(dao.write(boardTitle, boardUserID, boardUserName, boardUserMajor, boardContent, boardKind, caps, shirts, jackets, pants, shoes, bag, acce) == 1) {
			request.getSession().setAttribute("messageType", "성공 메시지");
			request.getSession().setAttribute("messageContent", "게시가 완료되었습니다.");
		}
	}
}
