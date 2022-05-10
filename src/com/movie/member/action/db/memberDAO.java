package com.movie.member.action.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class memberDAO {
	// 디비에 movie_member 테이블과 관련된 모든 동작을 처리
	
	// 공통변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";


	// 디비연결 메서드
	private Connection getCon() throws Exception{
		// 1.2. 디비 연결

		// 1) 프로젝트 정보를 초기화
		//   Context => 내 프로젝트를 의미
		Context initCTX = new InitialContext();
		// 2) 프로젝트에 저장된 리소스 정보를 불러오기
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/movie");
		// 3) 디비연결
		con = ds.getConnection();

		System.out.println("DAO : 디비연결 성공(커넥션풀) ");
		System.out.println("DAO : " + con);

		return con;
	}
	// 디비연결 메서드
	
	
	// 디비 자원해제 메서드
	public void closeDB() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
			
			System.out.println("DAO : 디비 연결 자원해제!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 디비 자원해제 메서드
	////////////////////////////////////////////////////////////////////

	
	
//	// 회원가입 - insertMember()
//	public void insertMember(MemberBean mb){
//		System.out.println("DAO : 회원가입 메서드 실행! ");
//		String DRIVER = "com.mysql.cj.jdbc.Driver";
//		String DBURL = "jdbc:mysql://localhost:3306/jspdb";
//		String DBID = "root";
//		String DBPW = "1234";
//		
//		    //* jsp 파일에서는 서버에서 제어해서 try-catch문 작성할 필요 없었음.
//			// 1. 드라이버 로드
//			try {
//				Class.forName(DRIVER);
//				
//				// 2. 디비 연결
//				con = DriverManager.getConnection(DBURL, DBID, DBPW);
//				
//				// 3. sql작성 & pstmt 생성
//				sql = "insert into itwill_member "
//						+ "values(?, ?, ?, ?, ?, ?, ?)";
//				pstmt = con.prepareStatement(sql);
//				// ??? 입력
//				pstmt.setString(1, mb.getId());
//				pstmt.setString(2, mb.getPw());
//				pstmt.setString(3, mb.getName());
//				pstmt.setString(4, mb.getGender());
//				pstmt.setInt(5, mb.getAge());
//				pstmt.setString(6, mb.getEmail());
//				pstmt.setTimestamp(7, mb.getReg_date());
//				
//				
//				// 4. sql 실행
//				pstmt.executeUpdate();
//				
//				System.out.println("DAO : 회원가입 완료!");
//				
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//				
//			} finally {
//				try {
//					// DB관련 동작이 모두 끝났을 때 객체 정보가 남아있는 경우 자원을 해제
//					// 사용한 순서의 역순으로 자원해제함(권장사항. 관습적으로 이렇게 많이 씀.)
//					if(pstmt != null) { pstmt.close(); };
//					if(con!=null) { con.close(); };
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//	} // 회원가입 - insertMember()
	
	
	
	// loginCheck(id,pw)
	public int loginCheck(String id, String pw) {
		int result = -1;

		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성(select) & pstmt 생성
			sql = "select pw from movie_member where id=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, id);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) { // 아이디에 해당하는 비밀번호가 있을 경우 => 회원
				if(pw.equals(rs.getString("pw"))) { // 입력받은 비밀번호와 디비에 저장된 비밀번호가 동일하다 => 본인
					result = 1;
				}else { // 입력받은 비밀번호와 디비에 저장된 비밀번호가 다르다
					result = 0;
				}
			}else { // 아이디에 해당하는 비밀번호가 없을 경우 => 비회원
				result = -1;
			}
			
			System.out.println("DAO : 로그인 체크완료! ("+result+")");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	} // 로그인 - loginCheck
	
	
//	// getMember(id)
//	public MemberBean getMember(String id) {
//		MemberBean mb = null;
//		
//		try {
//			// 1 2 디비연결
//			con = getCon();
//			
//			// 3. sql문 작성 & pstmt 객체 생성
//			sql = "select * from itwill_member where id=?";
//			pstmt = con.prepareStatement(sql);
//			// ???
//			pstmt.setString(1, id);
//			
//			// 4. sql 실행
//			rs = pstmt.executeQuery();
//			
//			// 5. 데이터 처리
//			if(rs.next()) { 
//				// 아이디에 해당하는 회원정보를 저장
//				mb = new MemberBean();
//				
//				mb.setId(rs.getString("id"));
//				mb.setPw(rs.getString("pw"));
//				mb.setName(rs.getString("name"));
//				mb.setGender(rs.getString("gender"));
//				mb.setAge(rs.getInt("age"));
//				mb.setEmail(rs.getString("email"));
//				mb.setReg_date(rs.getTimestamp("reg_date"));
//				}	
//			System.out.println("DAO : 회원정보 저장 완료!");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeDB();
//		}
//		
//		return mb;
//			
//	} // getMember
	
		
	
	
//	// updateMember(updateBean)
//	public int updateMember(MemberBean umb) {
//		int result = -1;
//		
//		try {
//			// 1 2 디비연결
//			con = getCon();
//			
//			// 3 sql 작성 & pstmt 객체 
//			sql="select pw from itwill_member where id=?";
//			pstmt = con.prepareStatement(sql);
//			// ???
//			pstmt.setString(1, umb.getId());
//			
//			// 4 sql 실행
//			rs = pstmt.executeQuery();
//			
//			// 5 데이터 처리
//			if(rs.next()) { // 수정할 정보가 있을 때(회원일 때)
//				if(umb.getPw().equals(rs.getString("pw"))) {
//					// 본인 -> 정보수정
//					
//					// 3 sql 작성(update) & pstmt 객체
//					sql = "update itwill_member set name=?, age=?, gender=? where id=?";
//					pstmt = con.prepareStatement(sql);
//					// ???
//					pstmt.setString(1, umb.getName());
//					pstmt.setInt(2, umb.getAge());
//					pstmt.setString(3, umb.getGender());
//					pstmt.setString(4, umb.getId());
//					
//					// 4 sql 실행
//					// result = 1;
//					result = pstmt.executeUpdate();		
//					
//				}else {
//					// 본인 x, 정보수정 x
//					result = 0;
//					System.out.println("DAO : 비밀번호 오류, 정보수정 X");
//				} // if
//				
//			}else {
//				result = -1;
//				System.out.println("DAO : 회원정보가 없음, 정보수정 X");
//			} // if
//			
//			System.out.println("DAO : 회원정보 수정완료! ( "+result+")");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeDB();
//		}
//
//		return result;
//	} // updateMember
	
	
	
//	// deleteMember(id,pw)
//	public int deleteMember(String id, String pw) {
//		int result = -1;
//		
//		try {
//			// 1 2 디비연결
//			con = getCon();
//			
//			// 3 sql 작성 & pstmt 객체 
//			sql="select pw from itwill_member where id=?";
//			pstmt = con.prepareStatement(sql);
//			// ???
//			pstmt.setString(1, id);
//			
//			// 4 sql 실행
//			rs = pstmt.executeQuery();
//			
//			// 5 데이터 처리
//			if(rs.next()) { // 수정할 정보가 있을 때(회원일 때)
//				if(pw.equals(rs.getString("pw"))) {
//					// 본인 -> 정보수정
//					
//					// 3 sql 작성(delete) & pstmt 객체
//					sql = "delete from itwill_member where id=?";
//					pstmt = con.prepareStatement(sql);
//					// ???
//					pstmt.setString(1, id);
//					
//					// 4 sql 실행
//					// result = 1;
//					result = pstmt.executeUpdate();		
//					
//				}else {
//					// 본인 x, 정보삭제 x
//					result = 0;
//					System.out.println("DAO : 비밀번호 오류, 정보삭제 X");
//				} // if
//				
//			}else {
//				result = -1;
//				System.out.println("DAO : 회원정보가 없음, 정보삭제 X");
//			} // if
//			
//			System.out.println("DAO : 회원정보 삭제완료! ( "+result+")");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeDB();
//		}
//		return result;
//	} // deleteMember
	
	
	
	
//	// getMemberList()
//	public ArrayList getMemberList() { 
//		ArrayList memberList = new ArrayList();
//		
//		try {
//			// 1 2 디비연결
//			con = getCon();
//			
//			// 3 sql 작성(select) & pstmt 객체 
//			sql = "select * from itwill_member";
//			pstmt = con.prepareStatement(sql);
//			
//			// 4 sql 실행
//			rs = pstmt.executeQuery();
//			
//			// 5 데이터 처리(DB 정보를 모두 저장)
//			while(rs.next()) {
//				// 회원 1명의 정보를 meberList 한칸에 저장
//				MemberBean mb = new MemberBean();
//						
//				mb.setAge(rs.getInt("age"));
//				mb.setEmail(rs.getString("email"));
//				mb.setGender(rs.getString("gender"));
//				mb.setId(rs.getString("id"));
//				mb.setName(rs.getString("name"));
//				mb.setPw(rs.getString("pw"));
//				mb.setReg_date(rs.getTimestamp("reg_date"));
//				System.out.println("회원 정보 저장 완료");
//				///////////////////////////////////////////////////////
//				
//				memberList.add(mb);
//				System.out.println("회원 리스트 한 칸에 저장 완료");
//			}
//			System.out.println("DAO : 회원 전체 목록 저장 완료!");
//			System.out.println(memberList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeDB();
//		}
//		
//		return memberList;
//	} // getMemberList
	
}
