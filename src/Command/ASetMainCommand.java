package Command;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BestDAO;
import DAO.BoardDAO;
import DTO.BoardDTO;

// 메인 홈페이지 설정
public class ASetMainCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String yearMonth = String.valueOf(year) + "-" + String.valueOf(month);
		BoardDAO dao = BoardDAO.getInstance();
		String latestDate = dao.latestDate();
		System.out.println("dao.latestDate()");
		
		if (!yearMonth.equals(latestDate)) {
			BestDAO bdao = BestDAO.getInstance();
			System.out.println("Update Date : " + year + " " + month + " " + day);
			bdao.Initialize();
			System.out.println("bdao.Initialize()");
			ArrayList<BoardDTO> bestList = dao.bestOfMonth(latestDate);
			dao.changeKind(bestList);
			System.out.println("dao.changeKind()");
			bestList = dao.makeMain();
			System.out.println("dao.makeMain()");
			for (int i = 0; i < bestList.size(); i++) { // 이거 돌리면 메인에 쓰일 BestDTO가 만들어짐.
				int boardNum = bestList.get(i).getBoardNum();
				String title = bestList.get(i).getBoardTitle();
				String userName = bestList.get(i).getBoardUserName();
				String userMajor = bestList.get(i).getBoardUserMajor();
				String content = bestList.get(i).getBoardContent();
				String boardKind = bestList.get(i).getBoardKind();
				Timestamp date = bestList.get(i).getBoardDate();
				bdao.DecideBest(boardNum, title, userName, userMajor, content, boardKind, date);
				System.out.println("bdao.DecideBest()");
			}
		}
	}
}
