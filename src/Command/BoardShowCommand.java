package Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.BoardDTO;

/* 해당 글 보여주기 */
public class BoardShowCommand implements ACommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		BoardDAO dao = BoardDAO.getInstance();
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		BoardDTO dto = dao.getBoard(boardNum);
		
		System.out.println(boardNum);
		
		dao.upHit(boardNum);
		request.setAttribute("dto", dto);
	}
}
