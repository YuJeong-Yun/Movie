package com.movie.main.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.movie.event.db.EventBoardDTO;
import com.movie.notice.db.NoticeBoardDTO;
import com.movie.review.db.MovieBoardDTO;


public class MainDAO {
	
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
	
	
	
	// 검색어 포함된 글 개수 반환 getBoardCount() 
	public List<Integer> getBoardCount(String search) {
		List<Integer> boardCnt = new ArrayList<Integer>();
		
		try {
			// 1.2. 디비연결
			con = getCon();
			
			// -------------------- movie_board 테이블 -----------------------
			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(*) from movie_board where subject like ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, "%"+search+"%"); // like에 ' ' 안 써도 됨!!
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				boardCnt.add(rs.getInt(1));
			}else {
				boardCnt.add(0);
			}
			
			// -------------------- movie_event_board 테이블 -----------------------
			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(*) from movie_event_board where subject like ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, "%"+search+"%"); // like에 ' ' 안 써도 됨!!
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				boardCnt.add(rs.getInt(1));
			}else {
				boardCnt.add(0);
			}
			
			// -------------------- movie_notice_board 테이블 -----------------------
			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(*) from movie_notice_board where subject like ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, "%"+search+"%"); // like에 ' ' 안 써도 됨!!
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				boardCnt.add(rs.getInt(1));
			}else {
				boardCnt.add(0);
			}
			
			System.out.println("DAO : 검색된 글개수 " + boardCnt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return boardCnt;
	} // getBoardCount()

	
	// 검색어 결과목록 가져오는 메서드
	public List<List> getBoardList(List boardCnt, String search) {
		List<List> searchList = new ArrayList<List>();
		List<MovieBoardDTO> reviewList = null;
		List<EventBoardDTO> eventList = null;
		List<NoticeBoardDTO> noticeList = null;
		
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// movie_board 게시판 검색 결과 있으면 최신 글 5개 리스트에 저장
			if((int)boardCnt.get(0) > 0) {
				reviewList = new ArrayList<>();
				
				// 3. sql 작성 & pstmt 객체
				// 검색어 포함글만 select 
				sql = "select * from (select @ROWNUM:=@ROWNUM +1 AS rownum, T.*	FROM movie_board T, (select @ROWNUM:=0) R "
						+ "	 ORDER BY num asc) sub where subject like ? order by sub.rownum desc limit 0, 5";
				pstmt = con.prepareStatement(sql);
				// ???
				pstmt.setString(1, "%"+search+"%");
				
				// 4. sql 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리 (list에 저장)
				while(rs.next()) {
					// MovieBoardDTO 객체의 정보를 ArrayList 한 칸에 저장
					MovieBoardDTO dto = new MovieBoardDTO();
					
					dto.setRownum(rs.getInt("rownum"));
					dto.setContent(rs.getString("content"));
					dto.setDate(rs.getTimestamp("date"));
					dto.setId(rs.getString("id"));
					dto.setIp(rs.getString("ip"));
					dto.setName(rs.getString("name"));
					dto.setNum(rs.getInt("num"));
					dto.setRe_cnt(rs.getInt("re_cnt"));
					dto.setReadcount(rs.getInt("readcount"));
					dto.setSubject(rs.getString("subject"));
					
					// DTO -> arrayList 한 칸에 저장
					reviewList.add(dto);
				}
				searchList.add(reviewList);
				
				System.out.println("DAO : reviewList 저장 완료 "+reviewList);
			}else {
				searchList.add(null);
			}
				
			// movie_event_board 게시판 검색 결과 있으면 최신 글 5개 리스트에 저장
			if((int)boardCnt.get(1) > 0) {
				eventList = new ArrayList<>();
				
				// 3. sql 작성 & pstmt 객체
				// 검색어 포함글만 select 
				sql = "select * from movie_event_board where subject like ? order by num desc limit 0, 6";
				pstmt = con.prepareStatement(sql);
				// ???
				pstmt.setString(1, "%"+search+"%");
				
				// 4. sql 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리 (list에 저장)
				while(rs.next()) {
					// EventBoardDTO 객체의 정보를 ArrayList 한 칸에 저장
					EventBoardDTO dto = new EventBoardDTO();
					
					dto.setCategory(rs.getString("category"));
					dto.setDate(rs.getTimestamp("date"));
					dto.setNum(rs.getInt("num"));
					dto.setSubject(rs.getString("subject"));
					dto.setEventDateStart(rs.getString("eventDateStart"));
					dto.setEventDateEnd(rs.getString("eventDateEnd"));
					dto.setImage(rs.getString("image"));
					dto.setImage_thumb(rs.getString("image_thumb"));
					
					// DTO -> arrayList 한 칸에 저장
					eventList.add(dto);
				}
				searchList.add(eventList);
				
				System.out.println("DAO : eventList 저장 완료 "+eventList);
			}else {
				searchList.add(null);
			}
			
			// movie_notice_board 게시판 검색 결과 있으면 최신 글 5개 리스트에 저장
			if((int)boardCnt.get(2) > 0) {
				noticeList = new ArrayList<>();
				
				// 3. sql 작성 & pstmt 객체
				// 검색어 포함글만 select 
				sql = "select * from (select @ROWNUM:=@ROWNUM +1 AS rownum, T.*	FROM movie_notice_board T, (select @ROWNUM:=0) R "
						+ "	 ORDER BY num asc) sub where subject like ? order by sub.rownum desc limit 0, 5";
				pstmt = con.prepareStatement(sql);
				// ???
				pstmt.setString(1, "%"+search+"%");
				
				// 4. sql 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리 (list에 저장)
				while(rs.next()) {
					NoticeBoardDTO dto = new NoticeBoardDTO();
					
					dto.setRownum(rs.getInt("rownum"));
					dto.setContent(rs.getString("content"));
					dto.setDate(rs.getTimestamp("date"));
					dto.setNum(rs.getInt("num"));
					dto.setReadcount(rs.getInt("readcount"));
					dto.setSubject(rs.getString("subject"));
					dto.setFile(rs.getString("file"));
					
					// DTO -> arrayList 한 칸에 저장
					noticeList.add(dto);
				}
				searchList.add(noticeList);
				
				System.out.println("DAO : noticeList 저장 완료 "+noticeList);
			}else {
				searchList.add(null);
			}
			
			System.out.println("DAO : 게시판 글 전체 목록 저장완료!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return searchList;
		
	} // getBoardList
	
}