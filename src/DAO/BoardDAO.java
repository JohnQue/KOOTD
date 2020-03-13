package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.BoardDTO;

public class BoardDAO {
	private Connection conn = null; // 데이터베이스를 접근하기 위한 객체
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 정보를 담을 수 있는 변수를 만들어준다.
	DataSource dataSource;
	/* Singleton Pattern */
	private static BoardDAO uniqInstance = new BoardDAO();

	private BoardDAO() {
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/TeamProject");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BoardDAO getInstance() {
		return uniqInstance;
	}

	public void connect() {
		// 생성자를 만들어준다.
		try {
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * // boardID 가져오는 함수 public int getNext() { connect(); String SQL =
	 * "SELECT bbsID FROM BBS ORDER BY bbsID DESC"; ResultSet rs = null; try { pstmt
	 * = conn.prepareStatement(SQL); rs = pstmt.executeQuery(); if (rs.next()) {
	 * return rs.getInt(1) + 1; } return 1;// 첫 번째 게시물인 경우 } catch (Exception e) {
	 * e.printStackTrace(); } finally { disconnect(); } return -1; // 데이터베이스 오류 }
	 */

	// 매 달 1일이 되면 각 게시판 별로 추천을 제일 많이 받은 게시물을, '이 달의'의 게시판으로 옮겨야 함.
	public int changeKind(ArrayList<BoardDTO> bestList) {
		int result = -1;
		String[] changeKinds = { "best", "funBest", "coord" };
		try {
			connect();
			for (BoardDTO obj : bestList) {
				String changeKind;
				if (obj.getBoardKind().equals("faKing") || obj.getBoardKind().equals("faQueen"))
					changeKind = changeKinds[0]; // faKing이나 faQueen이면 best로 바꿔줌
				else if (obj.getBoardKind().equals("funKing") || obj.getBoardKind().equals("funQueen"))
					changeKind = changeKinds[1]; // funKing이나 funQueen이면 funBest로 바꿔줌
				else
					changeKind = changeKinds[2]; // 그 외엔 coord만 남으니 coord로 바꿔줌
				String SQL = "UPDATE BOARD SET boardKind=? WHERE boardNum=?";
				/*
				 * String SQL =
				 * "UPDATE BOARD AS B, (select * from board where DATE_FORMAT(boardDate, '%Y-%m')=? AND boardKind=? ORDER BY boardLike DESC LIMIT 1) as A "
				 * + "SET B.boardKind = ? WHERE B.boardNum = A.boardNum;";
				 */
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, changeKind);
				pstmt.setInt(2, obj.getBoardNum());
				result = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	// 이번 달 가장 추천을 많이 받은 게시물들을 '이 달의' 게시판으로 옮기기 전에 가져오기
	public ArrayList<BoardDTO> bestOfMonth(String yearMonth) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String[] boardKinds = { "faKing", "faQueen", "funKing", "funQueen", "toMan", "toWoman" };
		try {
			connect();
			for (String boardKind : boardKinds) {
				String SQL = "select * from board where DATE_FORMAT(boardDate, '%Y-%m')=? AND boardKind=? ORDER BY boardLike DESC LIMIT 1";
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, yearMonth);
				pstmt.setString(2, boardKind);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					int boardNum = rs.getInt("boardNum");
					String boardTitle = rs.getString("boardTitle");
					String boardUserID = rs.getString("boarduserID");
					String boardUserName = rs.getString("boardUserName");
					String boardUserMajor = rs.getString("boardUserMajor");
					String boardContent = rs.getString("boardContent");
					Timestamp boardDate = rs.getTimestamp("boardDate");
					int boardHit = rs.getInt("boardHit");
					int boardLike = rs.getInt("boardLike");
					int boardDislike = rs.getInt("boardDislike");
					String cap = rs.getString("cap");
					String shirts = rs.getString("shirts");
					String jacket = rs.getString("jacket");
					String pants = rs.getString("pants");
					String shoes = rs.getString("shoes");
					String bag = rs.getString("bag");
					String acce = rs.getString("acce");
					list.add(new BoardDTO(boardNum, boardTitle, boardUserID, boardUserName, boardUserMajor,
							boardContent, boardDate, boardHit, boardLike, boardDislike, boardKind, cap, shirts, jacket,
							pants, shoes, bag, acce));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 이 달의 게시판에 넣을 게시물들 골라내기
	public ArrayList<BoardDTO> makeMain() {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String[] boardKinds = { "best", "funBest", "coord" };
		try {
			connect();
			for (String boardKind : boardKinds) {
				String SQL = "select * from board where boardKind=? ORDER BY boardDate DESC LIMIT 2";
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, boardKind);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int boardNum = rs.getInt("boardNum");
					String boardTitle = rs.getString("boardTitle");
					String boardUserID = rs.getString("boarduserID");
					String boardUserName = rs.getString("boardUserName");
					String boardUserMajor = rs.getString("boardUserMajor");
					String boardContent = rs.getString("boardContent");
					Timestamp boardDate = rs.getTimestamp("boardDate");
					int boardHit = rs.getInt("boardHit");
					int boardLike = rs.getInt("boardLike");
					int boardDislike = rs.getInt("boardDislike");
					String cap = rs.getString("cap");
					String shirts = rs.getString("shirts");
					String jacket = rs.getString("jacket");
					String pants = rs.getString("pants");
					String shoes = rs.getString("shoes");
					String bag = rs.getString("bag");
					String acce = rs.getString("acce");
					list.add(new BoardDTO(boardNum, boardTitle, boardUserID, boardUserName, boardUserMajor,
							boardContent, boardDate, boardHit, boardLike, boardDislike, boardKind, cap, shirts, jacket,
							pants, shoes, bag, acce));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 실제로 글을 작성하는 함수 (select * from (select max(boardNum)+1 from board) next)
	public int write(String boardTitle, String boardUserID, String boardUserName, String boardUserMajor,
			String boardContent, String boardKind, String cap, String shirts, String jacket, String pants, String shoes,
			String bag, String acce) {
		int result = -1;
		try {
			connect();
			String SQL = "INSERT INTO BOARD VALUES(NULL, ?, ?, ?, ?, ?, now(), 0, 0, 0, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardUserID);
			pstmt.setString(3, boardUserName);
			pstmt.setString(4, boardUserMajor);
			pstmt.setString(5, boardContent);
			pstmt.setString(6, boardKind);
			pstmt.setString(7, cap);
			pstmt.setString(8, shirts);
			pstmt.setString(9, jacket);
			pstmt.setString(10, pants);
			pstmt.setString(11, shoes);
			pstmt.setString(12, bag);
			pstmt.setString(13, acce);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result; // 데이터베이스 오류
	}

	// 게시판 목록 구현하기
	public ArrayList<BoardDTO> getList(String boardKind) {
		String SQL = "select * from board where boardKind=? ORDER BY boardDate DESC";

		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, boardKind);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto;
				int boardNum = rs.getInt("boardNum");
				String boardTitle = rs.getString("boardTitle");
				String boardUserID = rs.getString("boardUserID");
				String boardUserName = rs.getString("boardUserName");
				String boardUserMajor = rs.getString("boardUserMajor");
				String boardContent = rs.getString("boardContent");
				Timestamp boardDate = rs.getTimestamp("boardDate");
				int boardHit = rs.getInt("boardHit");
				int boardLike = rs.getInt("boardLike");
				int boardDislike = rs.getInt("boardDislike");
				String cap = rs.getString("cap");
				String shirts = rs.getString("shirts");
				String jacket = rs.getString("jacket");
				String pants = rs.getString("pants");
				String shoes = rs.getString("shoes");
				String bag = rs.getString("bag");
				String acce = rs.getString("acce");
				dto = new BoardDTO(boardNum, boardTitle, boardUserID, boardUserName, boardUserMajor, boardContent,
						boardDate, boardHit, boardLike, boardDislike, boardKind, cap, shirts, jacket, pants, shoes, bag,
						acce);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 가장 추천 많이 받은 게시물 5개 가져오기
	public ArrayList<BoardDTO> PreWinner(String boardKind, String yearMonth) {
		String SQL = "select * from board where DATE_FORMAT(boardDate, '%Y-%m')=? AND boardKind=? AND boardLike>0 ORDER BY boardLike DESC LIMIT 5";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, yearMonth);
			pstmt.setString(2, boardKind);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto;
				int boardNum = rs.getInt("boardNum");
				String boardTitle = rs.getString("boardTitle");
				String boardUserID = rs.getString("boardUserID");
				String boardUserName = rs.getString("boardUserName");
				String boardUserMajor = rs.getString("boardUserMajor");
				String boardContent = rs.getString("boardContent");
				Timestamp boardDate = rs.getTimestamp("boardDate");
				int boardHit = rs.getInt("boardHit");
				int boardLike = rs.getInt("boardLike");
				int boardDislike = rs.getInt("boardDislike");
				String cap = rs.getString("cap");
				String shirts = rs.getString("shirts");
				String jacket = rs.getString("jacket");
				String pants = rs.getString("pants");
				String shoes = rs.getString("shoes");
				String bag = rs.getString("bag");
				String acce = rs.getString("acce");
				dto = new BoardDTO(boardNum, boardTitle, boardUserID, boardUserName, boardUserMajor, boardContent,
						boardDate, boardHit, boardLike, boardDislike, boardKind, cap, shirts, jacket, pants, shoes, bag,
						acce);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	/*
	 * // 게시물이 10개라면 다음 페이지가 없어야 public boolean nextPage(int pageNumber) { ResultSet
	 * rs = null; String SQL =
	 * "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10"
	 * ; try { PreparedStatement pstmt = conn.prepareStatement(SQL); pstmt.setInt(1,
	 * getNext() - (pageNumber - 1) * 10); rs = pstmt.executeQuery(); if (rs.next())
	 * { return true; } } catch (Exception e) { e.printStackTrace(); } return false;
	 * }
	 */
	// boardKind 게시글 종류 넣기 searchKind 검색이 제목인지 사용자인지 SearchKwd 검색하려는 단어
	public ArrayList<BoardDTO> searchKwd(String boardKind, String searchKind, String SearchKwd) {
		ArrayList<BoardDTO> list = new ArrayList<>();
		String SQL = null;

		try {
			connect();
			if (searchKind.equals("작성자")) {
				SQL = "select * from board where boardKind=? and boardUserName LIKE ?;";

			} else{
				SQL = "select * from board where boardKind=? and boardTitle LIKE ?;";
			}
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, boardKind);
			pstmt.setString(2, "%"+SearchKwd+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto;
				int boardNum = rs.getInt("boardNum");
				String boardTitle = rs.getString("boardTitle");
				String boardUserID = rs.getString("boardUserID");
				String boardUserName = rs.getString("boardUserName");
				String boardUserMajor = rs.getString("boardUserMajor");
				String boardContent = rs.getString("boardContent");
				Timestamp boardDate = rs.getTimestamp("boardDate");
				int boardHit = rs.getInt("boardHit");
				int boardLike = rs.getInt("boardLike");
				int boardDislike = rs.getInt("boardDislike");
				String cap = rs.getString("cap");
				String shirts = rs.getString("shirts");
				String jacket = rs.getString("jacket");
				String pants = rs.getString("pants");
				String shoes = rs.getString("shoes");
				String bag = rs.getString("bag");
				String acce = rs.getString("acce");
				dto = new BoardDTO(boardNum, boardTitle, boardUserID, boardUserName, boardUserMajor, boardContent,
						boardDate, boardHit, boardLike, boardDislike, boardKind, cap, shirts, jacket, pants, shoes, bag, acce);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;

	}

	// 해당 게시물 불러오기
	public BoardDTO getBoard(int boardNum) {
		BoardDTO dto = null;
		String SQL = "SELECT * FROM BOARD WHERE boardNum = ?";
		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String boardTitle = rs.getString("boardTitle");
				String boardUserID = rs.getString("boardUserID");
				String boardUserName = rs.getString("boardUserName");
				String boardUserMajor = rs.getString("boardUserMajor");
				String boardContent = rs.getString("boardContent");
				Timestamp boardDate = rs.getTimestamp("boardDate");
				int boardHit = rs.getInt("boardHit");
				int boardLike = rs.getInt("boardLike");
				int boardDislike = rs.getInt("boardDislike");
				String boardKind = rs.getString("boardKind");
				String cap = rs.getString("cap");
				String shirts = rs.getString("shirts");
				String jacket = rs.getString("jacket");
				String pants = rs.getString("pants");
				String shoes = rs.getString("shoes");
				String bag = rs.getString("bag");
				String acce = rs.getString("acce");
				dto = new BoardDTO(boardNum, boardTitle, boardUserID, boardUserName, boardUserMajor, boardContent,
						boardDate, boardHit, boardLike, boardDislike, boardKind, cap, shirts, jacket, pants, shoes, bag,
						acce);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return dto;
	}

	// 글 업데이트 함수
	public int update(int boardNum, String boardTitle, String boardContent) {
		int result = -1;
		String SQL = "UPDATE BOARD SET boardTitle = ?, boardContent = ? WHERE boardNum = ?";
		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setInt(3, boardNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result; // -1이면 데이터 베이스 오류
	}

	// 글 삭제 함수
	public int remove(int boardNum) {
		int result = -1;
		PreparedStatement pstmt = null;
		String SQL = "DELETE FROM board WHERE boardNum = ?";
		try {
			connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result; // -1이면 디비오류
	}

	// 조회수
	public int upHit(int boardNum) {
		int result = -1;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE BOARD SET boardHit=boardHit+1 WHERE boardNum = ?";
		try {
			connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result; // -1이면 데이터베이스 오류
	}

	// 추천 클릭
	public int clickLike(int boardNum) {
		int result = -1;
		String SQL = "UPDATE BOARD SET boardLike=boardLike+1 WHERE boardNum = ?";
		try {
			connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result; // -1이면 데이터베이스 오류
	}

	// 비추천 클릭
	public int clickDislike(int boardNum) {
		int result = -1;
		String SQL = "UPDATE BOARD SET boardDislike=boardDislike+1 WHERE boardNum = ?";
		try {
			connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result; // -1이면 데이터베이스 오류
	}

	// 가장 마지막으로 올라온 글 날짜
	public String latestDate() {
		String date = null;
		try {
			connect();
			String SQL = "SELECT boardDate FROM board ORDER BY boardDate DESC LIMIT 1;";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String time = rs.getString(1);
				date = time.substring(0, 7);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return date;
	}
	
	// board에서 출력할 전공 변경
	public int changeInfo(String major, String userID) {
		int result = -1;
		String SQL = "UPDATE BOARD SET boardUserMajor=? WHERE boardUserID=?";
		try {
			connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, major);
			pstmt.setString(2, userID);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result; // -1이면 데이터베이스 오류
	}
}
