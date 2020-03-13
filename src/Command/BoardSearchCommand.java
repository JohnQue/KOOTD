 package Command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DTO.BoardDTO;

/* 검색 클래스 */
public class BoardSearchCommand implements ACommand {
   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      BoardDAO dao = BoardDAO.getInstance();
      String boardKind = request.getParameter("boardKind");
      String searchKwd = request.getParameter("searchKwd");
      String searchKind = request.getParameter("searchKind");
      System.out.println(boardKind+" "+searchKwd+" "+searchKind);
      request.setAttribute("boardKind", boardKind);
      ArrayList<BoardDTO> boardList = dao.searchKwd(boardKind, searchKind, searchKwd);
      request.getSession().setAttribute("boardList", boardList);
   }
}