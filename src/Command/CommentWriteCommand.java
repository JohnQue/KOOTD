package Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;
import DTO.UserDTO;

/* 댓글 달 때 호출하는 함수*/
public class CommentWriteCommand implements ACommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDTO account = (UserDTO)request.getSession().getAttribute("info");
		String userID =  account.getUserID();
		String userName = account.getUserName();
		String userMajor = account.getUserMajor();
		String userComment = request.getParameter("userComment");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		CommentDAO dao = CommentDAO.getInstance();
		dao.comment(userID, userName, userMajor, userComment, boardNum);
	}
}
