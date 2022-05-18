package com.movie.notice.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class NoticeBoardDAO {
	// 디비에 movie_board 테이블과 관련된 모든 동작을 처리
	
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
	
	
	
	// 현재 작성된 글 개수 반환 getBoardCount()
	public int getBoardCount() {
		int result = 0;
		
		try {
			// 1.2. 디비연결
			con = getCon();
			
			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(num) from movie_notice_board";
			pstmt = con.prepareStatement(sql);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			System.out.println("DAO : 게시판 글개수 " + result + "개");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	} // getBoardCount()
	
	
	// 글목록 가져오는 메서드
	public ArrayList<NoticeBoardDTO> getBoardList(int startRow, int pageSize) {
		ArrayList<NoticeBoardDTO> boardList = new ArrayList<>();
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체 (num 내림차순 정렬, 페이징 처리)
			//		=> limit 시작행(0부터 시작함), 개수
			sql = "select * from (select @ROWNUM:=@ROWNUM +1 AS rownum, T.*	FROM movie_notice_board T, (select @ROWNUM:=0) R "
					+ "	 ORDER BY num asc) sub order by sub.rownum desc limit ?, ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			// 데이터 있을 때 DB 정보를 모두 저장
			while(rs.next()) {
				NoticeBoardDTO dto = new NoticeBoardDTO();
				
				dto.setRownum(rs.getInt("rownum"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setNum(rs.getInt("num"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
				dto.setFile(rs.getString("file"));
				
				boardList.add(dto);
			}
			System.out.println("DAO : 공지사항 글 전체 목록 저장완료!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return boardList;
	} // getBoardList
	
	
	// 글 조회수 증가시키는 메서드
	public int updateReadCount(int num) {
		int result = 0;
		
		try {
			// 1.2 디비연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 생성
			// 관리자 아니면 기존 조회수 + 1 구문
			sql = "update movie_notice_board set readcount = readcount + 1 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			// 4. sql 실행
			result = pstmt.executeUpdate();
			
			System.out.println("DAO : "+num+"번 조회수 "+result +" 증가");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	} // updateReadCount
	
	
	// 글 1개의 정보를 반환
	public NoticeBoardDTO getBoard(int num) {
		NoticeBoardDTO dto = null;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체
			sql = "select * from movie_notice_board where num = ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 조회
			if(rs.next()) {
				dto = new NoticeBoardDTO();
				
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setNum(rs.getInt("num"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
				dto.setFile(rs.getString("file"));
			}
			System.out.println(" 데이터 입력 완료!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return dto;
	} // getBoard

	
	// 이전, 다음 글 번호 반환
	public int getBoardSideNum(int i, int num) {
		// i가 0이면 이전, 1이면 다음 글 번호 반환
		int result = 0;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체
			sql = (i==0) ? "select min(num) from movie_notice_board where num > ?" : "select max(num) from movie_notice_board where num < ?" ;
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				result = (i==0) ? rs.getInt(1) : rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	} // getBoardSideNum
	
	
	// 글쓰기 메서드
	public int insertBoard(NoticeBoardDTO dto) {
		int num = 0; // 글번호 저장 변수
		
		try {
			// 1.2. 디비연결
			con = getCon();
			
			// 3 sql 작성 (글 번호를 계산하는 sql) & pstmt 객체
			sql = "select max(num) from movie_notice_board";
			pstmt = con.prepareStatement(sql);

			// 4 sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {  
				// getInt 메서드는 null값일 경우 0을 반환
				num = rs.getInt(1)+1;   		
			}
			
			System.out.println("DAO  : 글번호 " + num);
			
			
			////////////////////////////////////////////////////////////
			// 글쓰기
			
			// 3. sql(insert) 작성 & pstmt 객체
			sql = "insert into movie_notice_board(num, subject, content, readcount, date, file) "
					+ "values (?, ?, ?, ?, now(), ?)";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);  // 직접 계산한 글번호
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, 0);
			pstmt.setString(5, dto.getFile());
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO : 글쓰기 완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return num;
	} // insertBoard
	
	
	// 글 삭제하는 메서드
	public int deleteBoard(int num) {
		int result = -1;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체
			sql = "delete from movie_notice_board where num=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);
			
			// 4. sql 실행
			// pstmt.executeUpdate()는 실행된 row 수를 반환함
			 result = pstmt.executeUpdate(); 
			
			System.out.println(" DAO : 게시판 글 삭제 완료! " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	} // deleteBoard
	
	
	// 수정 정보 DB에 업데이트하는 메서드
	public int updateBoard(NoticeBoardDTO dto) {
		int result = -1;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성(update) & pstmt 객체
			sql = "update movie_notice_board set subject=?, content=?, file=? where num=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getFile());
			pstmt.setInt(4, dto.getNum());
			
			// 4. sql 실행
			result = pstmt.executeUpdate();	
			
			System.out.println(" DAO : 정보 수정 완료("+result+")");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	} // updateBoard 
		
}