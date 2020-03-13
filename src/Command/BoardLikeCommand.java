package Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DAO.LikeDislikeDAO;
import DAO.UserDAO;

public class BoardLikeCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LikeDislikeDAO dao = LikeDislikeDAO.getInstance();
		String userID = request.getParameter("userID");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String ownerID = request.getParameter("ownerID");
		if(dao.isAlready(userID, boardNum) == 1) {
			// TODO : 추천 이미 눌렀을 경우
			request.setAttribute("messageType", "오류 메시지");
			request.setAttribute("messageContent", "이미 해당글에 추천/비추천을 하셨습니다.");
		}
		else {
			// TODO : 아무것도 안 눌렀을 경우
			dao.clickButton(userID, boardNum);
			BoardDAO bdao = BoardDAO.getInstance();
			bdao.clickLike(boardNum);
			UserDAO udao = UserDAO.getInstance();
			udao.addLike(ownerID);
			request.setAttribute("messageType", "성공 메시지");
			request.setAttribute("messageContent", "추천 하셨습니다!");
		}
	}

}
