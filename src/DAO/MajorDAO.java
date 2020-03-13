package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MajorDAO {
	private Connection conn = null; // 데이터베이스를 접근하기 위한 객체
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 정보를 담을 수 있는 변수를 만들어준다.
	DataSource dataSource;
	private static MajorDAO uniqInstance = new MajorDAO();
	public MajorDAO(){ // 생성자인 동시에 데이터베이스에 접근하게 해줆.
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/TeamProject");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static MajorDAO getInstance() {
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
	public ArrayList<String> getMajorList(String univ){
		ArrayList<String> majorList = new ArrayList<String>();
		String SQL = "SELECT usermajor FROM MAJOR WHERE university=?";
		try {
			connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, univ);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String usermajor = rs.getString("usermajor");
				majorList.add(usermajor);
			}
			return majorList;
		}catch(Exception e){
			e.printStackTrace();
		}finally { // sql문장이 실행이 끝나면 모든 리소스 종료
			disconnect();
		}
		return null;
	}
}
