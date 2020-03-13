package Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DAO.UserDAO;
import DTO.UserDTO;

public class UserModifyCommand implements ACommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String userUniv = request.getParameter("userUniv");
		String userMajor = request.getParameter("userMajor");
		String userPassword = request.getParameter("userPassword");
		String userPassword1 = request.getParameter("userPassword1");
		String userPassword2 = request.getParameter("userPassword2");
		String userEmail = request.getParameter("userEmail");
		String userProfile = request.getParameter("userProfile");
		UserDAO dao = UserDAO.getInstance();
		UserDTO info = (UserDTO)request.getSession().getAttribute("info");
		
		if(userUniv == null || userUniv.equals("") 
				|| userMajor == null || userMajor.equals("") ||userPassword1 == null || userPassword1.equals("")
				|| userPassword2 == null || userPassword2.equals("") || userEmail.equals("") || userEmail == null){
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			return;
		}
		if(!userPassword1.equals(userPassword2)) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "비밀번호가 서로 같지 않습니다.");
			return;
		}
		int result;
		result = dao.login(info.getUserID(), userPassword);
		if(result == 2) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "현재 비밀번호가 틀렸습니다.");
			return;
		}
		result = dao.modify(userUniv, userMajor, userPassword1, userEmail, userProfile, info.getUserID());
		if(result == 1) {
			request.getSession().setAttribute("messageType", "성공 메시지");
			request.getSession().setAttribute("messageContent", "회원정보 수정이 완료되었습니다.");
			info = dao.getUserinfo(info.getUserID());
			request.getSession().setAttribute("info", info);
			BoardDAO bdao = BoardDAO.getInstance();
			bdao.changeInfo(userMajor, info.getUserID());
			request.setAttribute("modify", "success");
			return;
		}
		else {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "데이터베이스 오류입니다.");
			return;
		}
	}
}
