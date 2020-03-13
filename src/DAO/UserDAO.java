package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.UserDTO;

public class UserDAO { // 실제로 데이터베이스에 접근해서 데이터를 가져오거나, 수정, 쓰는 역할
	DataSource dataSource;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	/* Singleton Pattern */
	private static UserDAO uniqInstance = new UserDAO();
	private UserDAO() {
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/TeamProject");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static UserDAO getInstance() {
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
	
	//로그인 메소드
	public int login(String userID, String userPassword) {
		int result = -1;
		try {
			connect();
			String SQL = "SELECT * FROM USER WHERE userID=?";
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("userPassword").equals(userPassword)) result = 1; // 로그인 성공
				else result = 2; // 비밀번호가 틀림
			}else {
				result = 0; // 아이디 존재 X
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally { // sql문장이 실행이 끝나면 모든 리소스 종료
			disconnect();
		}
		return result; // DB 오류시 -1리턴
	}
	
	//중복 체크
	public int registerCheck(String userID) { // 중복 체크랑, 숫자인지 체크, -1은 DB 오류, 0은 존재하는 계정, 1은 Ok, 2는 아이디 문제
		int result = -1;
		try {
			connect();
			try {
				if(userID.length() != 9) {
					return 2;
				}else if(Integer.parseInt(userID) < 201000000 || Integer.parseInt(userID) > 201900000) {
					return 2;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			String SQL = "SELECT * FROM USER WHERE userID=?";
			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, userID);
				rs = pstmt.executeQuery();
				if(rs.next() || userID.equals("")) {
					result = 0; // 이미 존재하거나, 빈칸임
				}else {
					result = 1; // 가입 가능한 회원 ID
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();// sql문장이 실행이 끝나면 모든 리소스 종료
		}
		return result; // 결과값 반환
	}
	
	//회원 등록
	public int register(String userID, String userUniv, String userMajor, String userPassword, String userName, String userAge, String userGender, String userEmail, String userProfile) { 
		int result = -1;
		try {
			connect();
			String SQL = "INSERT INTO USER VALUES(?, ?, ?, ?, ?, ?, ?, ?, 0, 0, ?)";
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userUniv);
			pstmt.setString(3, userMajor);
			pstmt.setString(4, userPassword);
			pstmt.setString(5, userName);
			pstmt.setInt(6, Integer.parseInt(userAge));
			pstmt.setString(7, userGender);
			pstmt.setString(8, userEmail);
			pstmt.setString(9, userProfile);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally { // sql문장이 실행이 끝나면 모든 리소스 종료
			disconnect();
		}
		return result; // DB 오류
	}
	
	//회원정보 수정
	public int modify(String userUniv, String userMajor, String userPassword, String userEmail, String userProfile, String hiddenID) {
		String SQL;
		int result = -1;
		SQL = "UPDATE USER SET userUniv=?, userMajor=?, userPassword=?, userEmail=?, userProfile=? WHERE userID=?";
		try {
			connect();
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userUniv);
			pstmt.setString(2, userMajor);
			pstmt.setString(3, userPassword);
			pstmt.setString(4, userEmail);
			pstmt.setString(5, userProfile);
			pstmt.setString(6, hiddenID);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return result; // -1은 DB 오류
	}
	
	//회원 정보 얻기
	public UserDTO getUserinfo(String userID) {
		UserDTO dto = null;
		String SQL = "SELECT * FROM USER WHERE userID=?";
		try {
			connect();
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new UserDTO(rs.getString("userID"), rs.getString("userUniv"), rs.getString("userMajor"), 
						rs.getString("userPassword"), rs.getString("userName"), 
						rs.getInt("userAge"), rs.getString("userGender"), rs.getString("userEmail"), 
						rs.getInt("totalLike"), rs.getInt("totalDislike"), rs.getString("userProfile"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return dto;
	}
	
	//회원 정보 전부 얻기
	public ArrayList<UserDTO> userlist() {
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String SQL = "SELECT * FROM USER";
		try {
			connect();
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserDTO dto = new UserDTO(rs.getString("userID"), rs.getString("userUniv"), rs.getString("userMajor"), 
						rs.getString("userPassword"), rs.getString("userName"), 
						rs.getInt("userAge"), rs.getString("userGender"), rs.getString("userEmail"), 
						rs.getInt("totalLike"), rs.getInt("totalDislike"), rs.getString("userProfile"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally { // sql문장이 실행이 끝나면 모든 리소스 종료
			disconnect();
		}
		return list;
	}
	// 추천 받을 때 마다 1씩 상승
	public int addLike(String userID) {
		connect();
		String SQL;
		int result = -1;
		SQL = "UPDATE USER SET totalLike = totalLike+1 WHERE userID=?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return result; // -1은 DB 오류
	}
	// 비추천 받을 때 마다 1씩 상승
	public int addDislike(String userID) {
		String SQL;
		int result = -1;
		SQL = "UPDATE USER SET totalDislike = totalDislike+1 WHERE userID=?";
		try {
			connect();
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return result; // -1은 DB 오류
	}
}
