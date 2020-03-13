package Command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BestDAO;
import DTO.BestDTO;

public class AGetMainCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BestDAO dao = BestDAO.getInstance();
		ArrayList<BestDTO> mainList = dao.getMain();
		System.out.println("dao.getMain()");
		ArrayList<String> pictures = new ArrayList<String>();
		for(BestDTO dto : mainList) {
			if(dto.getContent().contains("<img src")) {
				int startPoint = dto.getContent().indexOf("http");
				int endPoint = dto.getContent().indexOf("style")-2;
				String picture = dto.getContent().substring(startPoint, endPoint);
				pictures.add(picture);
			}
		}
		request.setAttribute("pictures", pictures);
		request.setAttribute("mainList", mainList);
	}

}
