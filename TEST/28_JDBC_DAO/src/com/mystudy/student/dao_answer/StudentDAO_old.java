package com.mystudy.student.dao_answer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mystudy.student.vo.StudentVO;

// XxxDAO, XxxDao : Data Access Object
// 데이터베이스(Database)와 연동해서 CRUD를 구현하는 클래스
// 데이터베이스(Database)와 연동해서 CRUD 작업을 할 수 있는 기능을 제공하는 클래스
public class StudentDAO_old {
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "mystudy2";
	private final String PASSWORD = "mystudypw";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	//SELECT : 데이터 1개 조회(ID)
	public StudentVO selectOne(String id) {
		StudentVO vo = null;
		//DB연결하고 SQL문 실행해서 결과값을 화면 출력
		try {
			//1. JDBC 드라이버 로딩
			Class.forName(DRIVER);
			
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ID, NAME, KOR, ENG, MATH, TOT, AVG ");
			sql.append("  FROM STUDENT ");
			sql.append(" WHERE ID = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			//4. SQL 실행 결과에 대한 처리
			//   - SELECT : 조회(검색) 데이터 결과 값에 대한 처리
			if (rs.next()) {
				vo = new StudentVO(rs.getString("ID"), 
						rs.getString("NAME"),
						rs.getInt("KOR"),
						rs.getInt("ENG"),
						rs.getInt("MATH"),
						rs.getInt("TOT"),
						rs.getDouble("AVG"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5. 클로징 처리에 의한 자원 반납
			close(conn, pstmt, rs);
		}
		return vo;
		
	}
	
	//SELECT : 데이터 1개 조회(VO) - ID로 검색
	public StudentVO selectOne(StudentVO vo) {
		return selectOne(vo.getId());
	}
	
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		//5. 클로징 처리에 의한 자원 반납
		try {
			if (rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void close(Connection conn, PreparedStatement pstmt) {
		//5. 클로징 처리에 의한 자원 반납
		try {
			if (pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//(실습)SELECT : 전체 데이터 조회(검색, 선택, 찾기)
	public List<StudentVO> selectAll() {
		//List<StudentVO> list = new ArrayList<StudentVO>();
		List<StudentVO> list = null;
		
		try {
			//1. JDBC 드라이버 로딩
			Class.forName(DRIVER);
			
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ID, NAME, KOR, ENG, MATH, TOT, AVG ");
			sql.append("  FROM STUDENT ");
//			sql.append(" WHERE 1 = 2 ");
			sql.append(" ORDER BY ID ");
//			sql.append(" ORDER BY NAME ");
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<StudentVO>();
			
			//4. SQL 실행 결과에 대한 처리
			//   - SELECT : 조회(검색) 데이터 결과 값에 대한 처리
			while (rs.next()) {
				StudentVO vo = new StudentVO(
						rs.getString("ID"), 
						rs.getString("NAME"), 
						rs.getInt("KOR"), 
						rs.getInt("ENG"), 
						rs.getInt("MATH"), 
						rs.getInt("TOT"), 
						rs.getDouble("AVG"));
				list.add(vo);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("[예외발생] " + e.getMessage());
			//e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	//INSERT : VO 객체를 받아서 입력 - insert(vo) : int
	public int insert(StudentVO vo) {
		int result = 0;
		
		try {
			//1. JDBC 드라이버 로딩
			Class.forName(DRIVER);
			
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			//3. Statement 문 실행(SQL 문 실행)
			//3-1. SQL문 실행을 위한 준비(PreparedStatement 객체 생성)
			String sql = "";
			sql += "INSERT INTO STUDENT ";
			sql += "       (ID, NAME, KOR, ENG, MATH, TOT, AVG) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?) ";
			System.out.println("sql : " + sql);
			
			pstmt = conn.prepareStatement(sql);
			
			//3-2. SQL 문장의 ? 위치에 값 설정(매칭, 대입, 입력)
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getKor());
			pstmt.setInt(4, vo.getEng());
			pstmt.setInt(5, vo.getMath());
			pstmt.setInt(6, vo.getTot());
			pstmt.setDouble(7, vo.getAvg());
			
			//3-3. SQL 실행요청
			//4. SQL 실행 결과에 대한 처리
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			result = -1;
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return result;
	}
	
	//UPDATE : VO 데이터를 받아서 수정 - update(vo) : int
	//- UPDATE : 이름(name) 받아서 이름을 수정 - updateName : int
	//- UPDATE : 국어점수 받아서 국어점수 수정 - updateKor : int
	//-- UPDATE : ID를 받아서 총점, 평균 재계산 처리 - updateTotAvg : int
	//DELETE : 키값(ID)를 받아서 삭제 - delete : int
	//DELETE : VO 데이터 받아서 삭제 - delete : int
	
	
}


















