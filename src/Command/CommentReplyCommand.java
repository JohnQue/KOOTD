package Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;
import DTO.CommentDTO;
import DTO.UserDTO;

public class CommentReplyCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CommentDAO dao = CommentDAO.getInstance();
		UserDTO dto = (UserDTO)request.getSession().getAttribute("info");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		int commentNum = Integer.parseInt(request.getParameter("commentNum"));
		String userComment = request.getParameter("userComment");
		
		CommentDTO cdto = dao.getComment(commentNum);
		dao.reply(dto.getUserID(), dto.getUserName(), dto.getUserMajor(), userComment, boardNum, cdto.getCommentGroup(), cdto.getCommentStep(), cdto.getCommentIndent());
	}

}
