package Command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;
import DTO.CommentDTO;

/* 댓글 단 거 불러오는 클래스 */
public class BoardSCommentCommand implements ACommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CommentDAO cdao = CommentDAO.getInstance();
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		ArrayList<CommentDTO> comList = cdao.getComments(boardNum);
		request.setAttribute("comList", comList);
	}
}
