package Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
/* 해당 글 삭제 */
public class BoardRemoveCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO dao = BoardDAO.getInstance();
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String boardKind = (String)request.getSession().getAttribute("boardKind");
		request.getSession().setAttribute("homepage", "board.do?boardKind="+boardKind);
		dao.remove(boardNum);
		
	}
}
