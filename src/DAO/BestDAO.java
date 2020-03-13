package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.BestDTO;

public class BestDAO {
	private Connection conn = null; // 데이터베이스를 접근하기 위한 객체
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 정보를 담을 수 있는 변수를 만들어준다.
	DataSource dataSource;
	/* Singleton Pattern */
	private static BestDAO uniqInstance = new BestDAO();

	private BestDAO() {
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/TeamProject");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BestDAO getInstance() {
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

	// 매달 1일 마다 메인 홈페이지에 걸 게시물들 초기화
	public int Initialize() {
		int result = -1;
		try {
			connect();
			String SQL = "DELETE FROM BEST";
			pstmt = conn.prepareStatement(SQL);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result; // 데이터베이스 오류
	}

	// 메인 홈페이지에 걸 게시물들
	public int DecideBest(int boardNum, String title, String userName, String userMajor, String content,
			String boardKind, Timestamp date) {
		int result = -1;
		try {
			connect();
			String SQL = "INSERT INTO BEST VALUES(?, ?, ?, ?, ?, ?, ?);";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardNum);
			pstmt.setString(2, title);
			pstmt.setString(3, userName);
			pstmt.setString(4, userMajor);
			pstmt.setString(5, content);
			pstmt.setString(6, boardKind);
			pstmt.setTimestamp(7, date);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	// 메인에 쓰일 게시물들 리스트
	public ArrayList<BestDTO> getMain() {
		ArrayList<BestDTO> list = new ArrayList<BestDTO>();
		try {
			connect();
			String SQL = "select * from best";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int boardNum = rs.getInt("boardNum");
				String title = rs.getString("title");
				String userName = rs.getString("userName");
				String userMajor = rs.getString("userMajor");
				String content = rs.getString("content");
				String boardKind = rs.getString("boardKind");
				Timestamp date =rs.getTimestamp("date");
				list.add(new BestDTO(boardNum, title, userName, userMajor, content, boardKind, date));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
}
