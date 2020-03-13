package Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserConfirmCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("info") != null) request.setAttribute("confirm", "success");
		else {
			request.getSession().setAttribute("messageContent", "로그인을 먼저하세요.");
			request.getSession().setAttribute("messageType", "오류 메시지");
		}
	}
}
