package Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogoutCommand implements ACommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("messageType", "로그아웃");
		request.getSession().setAttribute("messageContent", "로그아웃 되었습니다!");
		request.getSession().removeAttribute("info");
	}
}
