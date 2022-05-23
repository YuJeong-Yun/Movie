package com.movie.member.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
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

	
	
	// 회원가입 - insertMember()
	public void insertMember(MemberDTO dto){
	
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql작성 & pstmt 생성
			sql = "insert into movie_member(id, pw, name, gender, addr, tel, email, date, profile) values(?, ?, ?, ?, ?, ?, ?, now(), ?)";
			pstmt = con.prepareStatement(sql);
			// ??? 입력
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getGender());
			pstmt.setString(5, dto.getAddr());
			pstmt.setString(6, dto.getTel());
			pstmt.setString(7, dto.getEmail());
			pstmt.setString(8, dto.getProfile());
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO : 회원가입 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	} // 회원가입 - insertMember()
	
	
	
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

	
	
	// getMember(id)
	public MemberDTO getMember(String id) {
		MemberDTO dto = null;
		
		try {
			// 1 2 디비연결
			con = getCon();
			
			// 3. sql문 작성 & pstmt 객체 생성
			sql = "select * from movie_member where id=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, id);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) { 
				// 아이디에 해당하는 회원정보를 저장
				dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setAddr(rs.getString("addr"));
				dto.setTel(rs.getString("tel"));
				dto.setEmail(rs.getString("email"));
				dto.setProfile(rs.getString("profile"));
			}	
			System.out.println("DAO : 회원정보 저장 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return dto;
	} // getMember
	
		
	
	
	// updateMember(updateBean)
	public int updateMember(MemberDTO dto, int profileDel) {
		int result = -1;
		
		try {
			// 1 2 디비연결
			con = getCon();
			
			// 3 sql 작성 & pstmt 객체 
			sql="select pw from movie_member where id=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, dto.getId());
			
			// 4 sql 실행
			rs = pstmt.executeQuery();
			
			// 5 데이터 처리
			if(rs.next()) { // 수정할 정보가 있을 때(회원일 때)
				if(dto.getPw().equals(rs.getString("pw"))) { // 본인 -> 정보수정
					
					// 프로필 null이고 profileDel!=1 이면 프로필 업데이트 X ---------------------
					if(dto.getProfile()==null && profileDel!=1) {
						// 3 sql 작성(update) & pstmt 객체
						sql = "update movie_member set gender=?, addr=?, tel=?, email=? where id=?";
						pstmt = con.prepareStatement(sql);
						// ???
						pstmt.setString(1, dto.getGender());
						pstmt.setString(2, dto.getAddr());
						pstmt.setString(3, dto.getTel());
						pstmt.setString(4, dto.getEmail());
						pstmt.setString(5, dto.getId());
						
						System.out.println("DAO : 프로필 업데이트 X");
						
					// 프로필 파일 있거나 profileDel==1이면 프로필 업데이트 O -------------------
					} else {
						// 3 sql 작성(update) & pstmt 객체
						sql = "update movie_member set gender=?, addr=?, tel=?, email=?, profile=? where id=?";
						pstmt = con.prepareStatement(sql);
						// ???
						pstmt.setString(1, dto.getGender());
						pstmt.setString(2, dto.getAddr());
						pstmt.setString(3, dto.getTel());
						pstmt.setString(4, dto.getEmail());
						pstmt.setString(5, dto.getProfile());
						pstmt.setString(6, dto.getId());
						
						System.out.println("DAO : 프로필 업데이트 O");
					}
					
					// 4 sql 실행
					result = pstmt.executeUpdate(); // result = 1;
					
				}else {
					// 본인 x, 정보수정 x
					result = 0;
					System.out.println("DAO : 비밀번호 오류, 정보수정 X");
				} // if
				
			}else {
				result = -1;
				System.out.println("DAO : 회원정보가 없음, 정보수정 X");
			} // if
			
			System.out.println("DAO : 회원정보 수정완료! ( "+result+")");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	} // updateMember
	
	
	
	// deleteMember(id,pw)
	public int deleteMember(String id, String pw) {
		int result = -1;
		
		try {
			// 1 2 디비연결
			con = getCon();
			
			// 3 sql 작성 & pstmt 객체 
			sql="select pw from movie_member where id=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, id);
			
			// 4 sql 실행
			rs = pstmt.executeQuery();
			
			// 5 데이터 처리
			if(rs.next()) { // 삭제할 정보가 있을 때(회원일 때)
				if(pw.equals(rs.getString("pw"))) {
					// 본인 -> 정보삭제
					
					// 3 sql 작성(delete) & pstmt 객체
					sql = "delete from movie_member where id=?";
					pstmt = con.prepareStatement(sql);
					// ???
					pstmt.setString(1, id);
					
					// 4 sql 실행
					result = pstmt.executeUpdate(); // result = 1;
					
				}else {
					// 본인 x, 정보삭제 x
					result = 0;
					System.out.println(" DAO : 비밀번호 오류, 정보삭제 X");
				} // if
				
			}else {
				result = -1;
				System.out.println(" DAO : 회원정보가 없음, 정보삭제 X");
			} // if
			
			System.out.println(" DAO : 회원정보 삭제완료! ( "+result+")");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	} // deleteMember


	
	// 비밀번호 변경 
	public int updatePw(String id, String pwNow, String pwNew) {
		int result = -1;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체
			sql = "select pw from movie_member where id=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, id);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				if(pwNow.equals(rs.getString("pw"))) { // 본인 -> 비밀번호 업데이트
					// 3. sql 작성 & pstmt
					sql = "update movie_member set pw=? where id=?";
					pstmt = con.prepareStatement(sql);
					// ???
					pstmt.setString(1, pwNew);
					pstmt.setString(2, id);
					
					// 4. sql 실행
					result = pstmt.executeUpdate(); // result = 1
				} else { // 비밀번호 오류
					result = 0;
					System.out.println("DAO : 비밀번호 오류, 비밀번호 업데이트 X");
				}
			} else { // 
				result = -1;
				System.out.println("DAO : 회원정보가 없음, 비밀번호 업데이트 X");
			} // if
			
			System.out.println("DAO : 비밀번호 업데이트 완료! "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	} // updatePw
	
	
	
}
