package FrontCon;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.*;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	
	public void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("actionDo");
		request.setCharacterEncoding("UTF-8");
		
		ACommand command = null;
		String viewPage = null;
		String homepage = null;
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		/* *.do는 해당 페이지에서만 해결해야 하는 것(회원가입시 비밀번호가 틀린다거나)
		 * 하는 것들에서는 적용될 수 없음. 오로지 다른 페이지로 넘어갈 때 필요.
		 * 예를들어 아이디 중복 체크나, 위에 쓴 회원가입시 비밀번호가 틀려서 다시
		 * 적어야 한다거나, 회원가입시에 뭔가 빈칸이 있다거나 등등 실시간으로 검사해야 하는 건
		 * 서블릿으로 해줘야 하는 모양.(Ajax)
		 * 아닐 수도 있지만 현재까지 알기론 그렇다. (실제로 몇 가지 실험삼아 돌려보니 안 되더라.)  
		 * **************************************************************************************/
		
		if(com.equals("/main.do")) { //메인 홈페이지로
			System.out.println("main");
			System.out.println("The Web is Starting now....");
			System.out.println("Checking the changes of main Page...");
			command = new ASetMainCommand();
			command.execute(request, response);
			command = new AGetMainCommand();
			command.execute(request, response);
			System.out.println("불러오기");
			viewPage = "main.jsp";
		}else if(com.equals("/join.do")) { // 회원가입 버튼 누를 때
			System.out.println("join");
			viewPage = "join.jsp"; 
		}else if(com.equals("/joinOk.do")) { // 회원가입 버튼 누를 때
			System.out.println("joinOk");
			command = new UserJoinCommand();
			command.execute(request, response);
			if(request.getAttribute("join").equals("success")) viewPage = "main.do";
			else viewPage = "join.jsp";
		}else if(com.equals("/login.do")) { // 로그인 버튼 누를 때
			System.out.println("login");
			String boardNum = request.getParameter("boardNum");
			String boardKind = request.getParameter("boardKind");
			System.out.println("login "+boardNum+" "+boardKind);
			if(boardNum != null) viewPage="login.jsp?boardNum="+boardNum;
			else if(boardKind != null) viewPage="login.jsp?boardKind="+boardKind;
			else viewPage = "login.jsp";	
		}else if(com.equals("/loginOk.do")){ // 로그인 완료
			System.out.println("loginOk");
			command = new UserLoginCommand();
			command.execute(request, response);
			if(request.getAttribute("login") != null) {
				String boardNum = request.getParameter("boardNum");
				String boardKind = request.getParameter("boardKind");
				System.out.println("loginOk "+boardKind+" "+boardNum);
				if(boardKind == null && boardNum == null)
					viewPage = "main.do";
				else if(boardKind != null)
					viewPage = "board.do?boardKind=" +boardKind;
				else viewPage = "boardView.do?boardNum=" +boardNum;
			}
			else viewPage = "login.do";
			request.removeAttribute("login");
		}else if(com.equals("/isLogin.do")){ // 로그인 확인
			System.out.println("isLogin");
			command = new UserConfirmCommand();
			command.execute(request, response);
			String boardKind = request.getParameter("boardKind");
			String boardNum = request.getParameter("boardNum");
			System.out.println("isLogin "+boardKind+" "+boardNum);
			if(request.getAttribute("confirm") != null) {
				if(boardKind == null && boardNum == null)
					viewPage = "main.do";
				else if(boardKind != null)
					viewPage = "board.do?boardKind=" +boardKind;
				else viewPage = "boardView.do?boardNum=" +boardNum;
			}
			else { 
				if(boardKind == null && boardNum == null)
					viewPage = "login.do";
				else if(boardKind != null)
					viewPage = "login.do?boardKind=" +boardKind;
				else viewPage = "login.do?boardNum=" +boardNum;
			}
			request.removeAttribute("confirm");
		}else if(com.equals("/logout.do")) { // 로그아웃 버튼 누를 때
			System.out.println("logout");
			command = new UserLogoutCommand();
			command.execute(request, response);
			viewPage = "main.do";
		}else if(com.equals("/myPage.do")) { // 마이 페이지 버튼 누를 때
			System.out.println("myPage");
			viewPage = "myPage.jsp";
		}else if(com.equals("/userModify.do")) { // 유저 개인정보 수정시 개인정보 가져옴
			System.out.println("userModify");
			viewPage = "userModify.jsp";
		}else if(com.equals("/modifyOk.do")){ // 유저 개인정보 수정여부 검사
			System.out.println("modifyOk");
			command = new UserModifyCommand();
			command.execute(request, response);
			if(request.getAttribute("modify") != null) viewPage = "myPage.do";
			else viewPage = "userModify.do";
			request.removeAttribute("modify");
		}else if(com.equals("/board.do")) { // 게시판 리스트 보여줌
			System.out.println("board");
			command = new BoardListCommand();
			command.execute(request, response);
			System.out.println(request.getAttribute("boardKind"));
			viewPage = "board.jsp";
		}else if(com.equals("/search.do")) { // 글 검색
			System.out.println("search");
			command = new BoardSearchCommand();
			command.execute(request, response);
			viewPage = "board.jsp";
		}else if(com.equals("/boardView.do")) { // 해당 글 보여줌
			System.out.println("boardView");
			command = new BoardShowCommand();
			command.execute(request, response);
			command = new BoardSCommentCommand();
			command.execute(request, response);
			viewPage = "boardView.jsp";
		}else if(com.equals("/writeView.do")) { // 글 쓰기 뷰
			System.out.println("writeView");
			String boardKind = request.getParameter("boardKind");
			System.out.println(boardKind);
			request.setAttribute("boardKind", boardKind);
			viewPage = "writeView.jsp";
		}else if(com.equals("/write.do")) { // 글 쓰고 난 다음 올리기 클릭시
			System.out.println("write");
			command = new BoardWriteCommand();
			command.execute(request, response);
			String boardKind = request.getParameter("boardKind");
			viewPage = "board.do?boardKind=" + boardKind;
		}else if(com.equals("/modifyView.do")) { // 글 수정 뷰
			System.out.println("modify");
			command = new BoardShowCommand();
			command.execute(request, response);
			viewPage = "modifyView.jsp";
		}else if(com.equals("/update.do")) { // 글 수정 다하고 수정 클릭시
			System.out.println("modifyComplete");
			command = new BoardModifyCommand();
			command.execute(request, response);
			homepage = (String)request.getSession().getAttribute("homepage");
			viewPage = homepage;
			request.getSession().removeAttribute("homepage");
		}else if(com.equals("/remove.do")) { // 삭제 버튼 클릭 시
			System.out.println("remove");
			command = new BoardRemoveCommand();
			command.execute(request, response);
			String boardKind = request.getParameter("boardKind");
			viewPage = "board.do?boardKind="+boardKind;
		}else if(com.equals("/comment.do")) { // 댓글 달 때
			System.out.println("comment");
			String boardNum = request.getParameter("boardNum");
			command = new CommentWriteCommand();
			command.execute(request, response);
			viewPage = "boardView.do?boardNum="+boardNum;
		}else if(com.equals("/modifyComment.do")) { // 댓글 수정 할 때
			System.out.println("modifyComment");
			String boardNum = request.getParameter("boardNum");
			System.out.println(boardNum);
			command = new CommentModifyCommand();
			command.execute(request, response);
			viewPage = "boardView.do?boardNum="+boardNum;
		}else if(com.equals("/replyComment.do")){
			System.out.println("replyComment");
			String boardNum = request.getParameter("boardNum");
			System.out.println(boardNum);
			command = new CommentReplyCommand();
			command.execute(request, response);
			viewPage = "boardView.do?boardNum="+boardNum;
		}else if(com.equals("/removeComment.do")) { // 댓글 삭제
			System.out.println("removeComment");
			command = new CommentDeleteCommand();
			command.execute(request, response);
			String boardNum = request.getParameter("boardNum");
			viewPage = "boardView.do?boardNum="+boardNum;
		}else if(com.equals("/clickLike.do")) { // 추천 버튼 누를 시
			System.out.println("clickLike");
			command = new BoardLikeCommand();
			command.execute(request, response);
			String boardNum = request.getParameter("boardNum");
			viewPage = "boardView.do?boardNum="+boardNum;
		}else if(com.equals("/clickDislike.do")) { // 비 추천 버튼 누를 시
			System.out.println("clickDislike");
			command = new BoardDislikeCommand();
			command.execute(request, response);
			String boardNum = request.getParameter("boardNum");
			viewPage = "boardView.do?boardNum="+boardNum;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
