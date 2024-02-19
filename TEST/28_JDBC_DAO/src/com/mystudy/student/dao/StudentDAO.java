package com.mystudy.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mystudy.common.CommonJDBCUtil;
import com.mystudy.student.vo.StudentVO;

// XxxDAO, XxxDao : Data Access Object
// 데이터베이스(Database)와 연동해서 CRUD를 구현하는 클래스
// 데이터베이스(Database)와 연동해서 CRUD 작업을 할 수 있는 기능을 제공하는 클래스
public class StudentDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	//SELECT : 데이터 1개 조회(ID)
	public StudentVO selectOne(String id) {
		StudentVO vo = null;
		try {
			conn = CommonJDBCUtil.getConnection();
			
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		return vo;
		
	}
	
	public ArrayList<StudentVO> selectAllID(ArrayList<String> arr) {
		ArrayList<StudentVO> stuArr = new ArrayList<StudentVO>();
		try {
			
			conn = CommonJDBCUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ID, NAME, KOR, ENG, MATH, TOT, AVG ");
			sql.append("  FROM STUDENT ");
			sql.append(" WHERE ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			for(int i = 0; i < arr.size(); i++) {
				pstmt.setString(1, arr.get(i));
				
				System.out.println(rs);
				
				rs = pstmt.executeQuery();
				
				
				if (rs.next()) {
					stuArr.add(new StudentVO(rs.getString("ID"), 
							rs.getString("NAME"), 
							rs.getInt("KOR"), 
							rs.getInt("ENG"), 
							rs.getInt("MATH"), 
							rs.getInt("TOT"), 
							rs.getDouble("AVG")));
				
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		return stuArr;
	}
	
	//SELECT : 데이터 1개 조회(VO) - ID로 검색
	public StudentVO selectOne(StudentVO vo) {
		return selectOne(vo.getId());
	}
	
	public ArrayList<StudentVO> selectAllVO(ArrayList<StudentVO> arr) {
		ArrayList<String> arrID = new ArrayList<String>();
		for (StudentVO vo : arr) {
			arrID.add(vo.getId());
		}
		return selectAllID(arrID);
	}
	
	public void insertOne(StudentVO vo) {
		try {
			
			conn = CommonJDBCUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO STUDENT ");
			sql.append(" (ID, NAME, KOR, ENG, MATH, TOT, AVG) ");
			sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getKor());
			pstmt.setInt(4, vo.getEng());
			pstmt.setInt(5, vo.getMath());
			pstmt.setInt(6, vo.getTot());
			pstmt.setDouble(7, vo.getAvg());
			
			int result = pstmt.executeUpdate();
			
			if (result == 0) System.out.println("인서트 실패!!");
			else System.out.println("인서트 성공!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}
	}
	
	public void DeleteOne(StudentVO vo) {
		try {
			conn = CommonJDBCUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM STUDENT WHERE ID = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getId());
			
			int result = pstmt.executeUpdate();
			
			if (result == 0) System.out.println("삭제에 실패하였습니다.");
			else System.out.println("삭제에 성공했습니다!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}
	}
	
	public void UpdateAll(StudentVO vo) {
		try {
			conn = CommonJDBCUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE STUDENT"
					+ " SET NAME = ?, KOR = ?, ENG = ?, MATH = ?, TOT = ?, AVG = ? "
					+ "WHERE ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			int i = 1;
			pstmt.setString(i++, vo.getName());
			pstmt.setInt(i++, vo.getKor());
			pstmt.setInt(i++, vo.getEng());
			pstmt.setInt(i++, vo.getMath());
			pstmt.setInt(i++, vo.getTot());
			pstmt.setDouble(i++, vo.getAvg());
			pstmt.setString(i++, vo.getId());
			
			int result = pstmt.executeUpdate();
			
			if (result == 0) System.out.println("업데이트에 실패하였습니다.");
			else System.out.println("업데이트에 성공했습니다!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}
	}
	public void UpdateKor(String id, int kor) {
	    updateScore(id, kor, "KOR");
	}

	public void UpdateEng(String id, int eng) {
	    updateScore(id, eng, "ENG");
	}

	public void UpdateMath(String id, int math) {
	    updateScore(id, math, "MATH");
	}
	
	private void updateScore(String id, int score, String subject) {
	    StringBuilder sql = new StringBuilder();
	    sql.append("UPDATE STUDENT SET ")
	       .append(subject)
	       .append(" = ?, TOT = ?, AVG = ? WHERE ID = ?");
	    StudentVO vo = selectOne(id);
	    
	    // 과목별 점수 업데이트 로직 필요 없이, 과목에 따라 score가 알맞게 설정됨.
	    if ("KOR".equals(subject)) {
	        vo.setKor(score);
	    } else if ("ENG".equals(subject)) {
	        vo.setEng(score);
	    } else if ("MATH".equals(subject)) {
	        vo.setMath(score);
	    } else {
	    	System.out.println("VO점수 set코드에 문제가 생김.");
	    }

	    UpdateFunction(score, vo, sql);
	}
	
	private void UpdateFunction(int score, StudentVO vo, StringBuilder sql) {
		try {
			conn = CommonJDBCUtil.getConnection();
			
			
			pstmt = conn.prepareStatement(sql.toString());
			int i = 1;
			pstmt.setInt(i++, score);
			pstmt.setInt(i++, vo.getTot());
			pstmt.setDouble(i++, vo.getAvg());
			pstmt.setString(i++, vo.getId());
			
			int result = pstmt.executeUpdate();
			
			if (result == 0) System.out.println("업데이트에 실패하였습니다.");
			else System.out.println("업데이트에 성공했습니다!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}		
	}
	
	
}
