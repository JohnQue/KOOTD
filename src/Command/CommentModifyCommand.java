package Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;

public class CommentModifyCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CommentDAO dao = CommentDAO.getInstance();
		int commentNum = Integer.parseInt(request.getParameter("commentNum"));
		String userComment = request.getParameter("userComment");
		dao.modify(userComment, commentNum);
	}

}
