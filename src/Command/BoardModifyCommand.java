package Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.BoardDTO;
/* 게시판 글 수정 */
public class BoardModifyCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO dao = BoardDAO.getInstance();
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String boardTitle = request.getParameter("title");
		String boardContent = request.getParameter("content");
		dao.update(boardNum, boardTitle, boardContent);
		BoardDTO dto = dao.getBoard(boardNum);
		request.getSession().setAttribute("dto", dto);
	}
}
