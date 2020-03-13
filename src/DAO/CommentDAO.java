package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.CommentDTO;

public class CommentDAO {
	private Connection conn = null; // 데이터베이스를 접근하기 위한 객체
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 정보를 담을 수 있는 변수를 만들어준다.
	DataSource dataSource;
	/* Singleton Pattern */
	private static CommentDAO uniqInstance = new CommentDAO();
	private CommentDAO() {
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/TeamProject");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static CommentDAO getInstance() {
		return uniqInstance;
	}
	public void connect(){
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
	//댓글 DB에 삽입
	public int comment(String userID, String userName, String userMajor, String userComment, int boardNum) {
		int result = -1;
		try {
			connect();
			int currval = 1;
			String SQL = "select ifnull(max(commentNum), 0)+1 from comment";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) currval = rs.getInt(1);
			SQL = "INSERT INTO COMMENT VALUES(NULL, ?, ?, ?, ?, ?, ?, 0, 0, now());";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userName);
			pstmt.setString(3, userMajor);
			pstmt.setString(4, userComment);
			pstmt.setInt(5, boardNum);
			pstmt.setInt(6, currval);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return result; // -1이면 데이터베이스 오류
	}
	
	public int remove(int commentNum) { // 댓글 삭제
		int result = -1;
		try {
			connect();
			String SQL = "DELETE FROM COMMENT WHERE commentNum=?;";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, commentNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return result; // -1이면 데이터베이스 오류
	}

	public int modify(String userComment, int commentNum) {
		int result = -1;
		try {
			connect();
			String SQL = "UPDATE COMMENT SET userComment=? WHERE commentNum=?;";
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userComment);
			pstmt.setInt(2, commentNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disconnect();
		}	
		return result;
	}
	
	//댓글에 답글 달아줌
	public int reply(String userID, String userName, String userMajor, String userComment, int boardNum, int commentGroup, int commentStep, int commentIndent) {
		int result = -1;
		replyShape(commentGroup, commentStep);
		try {
			connect();
			String SQL = "INSERT INTO COMMENT VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, now());";
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userName);
			pstmt.setString(3, userMajor);
			pstmt.setString(4, userComment);
			pstmt.setInt(5, boardNum);
			pstmt.setInt(6, commentGroup);
			pstmt.setInt(7, commentStep+1);
			pstmt.setInt(8, commentIndent+1);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}	
		return result;
	}
	
	private int replyShape(int commentGroup, int commentStep) {
		// TODO Auto-generated method stub
		int result = -1;
		try {
			connect();
			String SQL = "UPDATE COMMENT SET commentStep = commentStep + 1 where commentGroup = ? and commentStep > ?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, commentGroup);
			pstmt.setInt(2, commentStep);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	public CommentDTO getComment(int commentNum){
		CommentDTO dto = null;
		try {
			connect();
			String SQL = "SELECT * FROM COMMENT WHERE commentNum="+commentNum+";";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String userID = rs.getString("userID");
				String userName = rs.getString("userName");
				String userMajor = rs.getString("userMajor");
				String userComment = rs.getString("userComment");
				int boardNum = rs.getInt("boardNum");
				int commentGroup = rs.getInt("commentGroup");
				int commentStep = rs.getInt("commentStep");
				int commentIndent = rs.getInt("commentIndent");
				Timestamp commentDate = rs.getTimestamp("commentDate");
				dto = new CommentDTO(commentNum, userID, userName, userMajor, userComment, boardNum, commentGroup, commentStep, commentIndent, commentDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return dto;
	}
	public ArrayList<CommentDTO> getComments(int boardNum){
		ArrayList<CommentDTO> comList = new ArrayList<CommentDTO>();
		try {
			connect();
			String SQL = "SELECT * FROM COMMENT WHERE boardNum="+boardNum+" ORDER BY commentGroup ASC, commentStep ASC";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int commentNum = rs.getInt("commentNum");
				String userID = rs.getString("userID");
				String userName = rs.getString("userName");
				String userMajor = rs.getString("userMajor");
				String userComment = rs.getString("userComment");
				int commentGroup = rs.getInt("commentGroup");
				int commentStep = rs.getInt("commentStep");
				int commentIndent = rs.getInt("commentIndent");
				Timestamp commentDate = rs.getTimestamp("commentDate");
				CommentDTO com = new CommentDTO(commentNum, userID, userName, userMajor, userComment, boardNum, commentGroup, commentStep, commentIndent, commentDate);
				comList.add(com);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return comList;
	}
}
