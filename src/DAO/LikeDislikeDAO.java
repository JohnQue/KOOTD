package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LikeDislikeDAO {
	private Connection conn = null; // 데이터베이스를 접근하기 위한 객체
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 정보를 담을 수 있는 변수를 만들어준다.
	DataSource dataSource;
	/* Singleton Pattern */
	private static LikeDislikeDAO uniqInstance = new LikeDislikeDAO();

private LikeDislikeDAO() {
	try {
		InitialContext initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		dataSource = (DataSource) envContext.lookup("jdbc/TeamProject");
	}catch(Exception e) {
		e.printStackTrace();
	}
}

	public static LikeDislikeDAO getInstance() {
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

	public int clickButton(String userID, int boardNum) {
		int result = -1;
		try {
			connect();
			String SQL = "INSERT INTO LIKEDISLIKE VALUES(?, ?)";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setInt(2, boardNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return result; // 데이터베이스 오류
	}
	
	public int isAlready(String userID, int boardNum) {
		int result = 0;
		try {
			connect();
			String SQL = "SELECT * FROM LIKEDISLIKE WHERE userID=? AND boardNum=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setInt(2, boardNum);
			rs = pstmt.executeQuery();
			if(rs.next()) result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return result; // 1이면 이미 추천했고, 0이면 안했음
	}
}
