package Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.BoardDTO;

/* 게시판 글 출력 클래스 */
public class BoardListCommand implements ACommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		String yearMonth = String.valueOf(year)+"-"+String.valueOf(month);
		BoardDAO dao = BoardDAO.getInstance();
		String boardKind = request.getParameter("boardKind");
		request.setAttribute("boardKind", boardKind);
		ArrayList<BoardDTO> boardList = dao.getList(boardKind);
		if(!boardKind.equals("best") && !boardKind.equals("funBest") && !boardKind.equals("coord")) {
			ArrayList<BoardDTO> preWinList = dao.PreWinner(boardKind, yearMonth);
			request.getSession().setAttribute("preWinList", preWinList);
		}
		request.getSession().setAttribute("boardList", boardList);			
	}
}
