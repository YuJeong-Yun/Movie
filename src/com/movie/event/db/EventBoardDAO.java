package com.movie.event.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class EventBoardDAO {
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
	public int getBoardCount(String category) {
		int result = 0;
		
		try {
			// 1.2. 디비연결
			con = getCon();
			
			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(num) from movie_event_board where category=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, category);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			System.out.println("DAO : 카테고리 글개수 " + result + "개");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	} // getBoardCount()
	
	
	// 글목록 가져오는 메서드
	public ArrayList<EventBoardDTO> getBoardList(int startRow, int pageSize, String category) {
		ArrayList<EventBoardDTO> boardList = new ArrayList<>();
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체 (num 내림차순 정렬, 페이징 처리)
			//		=> limit 시작행(0부터 시작함), 개수
			sql = "select * from (select @ROWNUM:=@ROWNUM +1 AS rownum, T.*	FROM movie_event_board T, (select @ROWNUM:=0) R "
					+ "	 where category=? ORDER BY num asc) sub order by sub.rownum desc limit ?, ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, category);
			pstmt.setInt(2, startRow-1);
			pstmt.setInt(3, pageSize);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			// 데이터 있을 때 DB 정보를 모두 저장
			while(rs.next()) {
				EventBoardDTO dto = new EventBoardDTO();
				
				dto.setCategory(rs.getString("category"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setRownum(rs.getInt("rownum"));
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setEventDateStart(rs.getString("eventDateStart"));
				dto.setEventDateEnd(rs.getString("eventDateEnd"));
				dto.setImage(rs.getString("image"));
				dto.setImage_thumb(rs.getString("image_thumb"));
				
				boardList.add(dto);
			}
			System.out.println("DAO : 이벤트 글 전체 목록 저장완료!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return boardList;
	} // getBoardList
	
	
	// 이전, 다음 글 번호 반환
	public int getBoardSideNum(int i, int num, String category) {
		// i가 0이면 이전, 1이면 다음 글 번호 반환
		int result = 0;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체
			sql = (i==0) ? "select min(num) from movie_event_board where num>? and category=?" :
							"select max(num) from movie_event_board where num<? and category=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);
			pstmt.setString(2, category);
			
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
	
	
	// 글 1개의 정보를 반환
	public EventBoardDTO getBoard(int num) {
		EventBoardDTO dto = null;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체
			sql = "select * from movie_event_board where num = ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 조회
			if(rs.next()) {
				dto = new EventBoardDTO();
				
				dto.setCategory(rs.getString("category"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setEventDateStart(rs.getString("eventDateStart"));
				dto.setEventDateEnd(rs.getString("eventDateEnd"));
				dto.setImage(rs.getString("image"));
				dto.setImage_thumb(rs.getString("image_thumb"));
			}
			System.out.println(" 데이터 입력 완료!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return dto;
	} // getBoard

	
	// 글쓰기 메서드
	public int insertBoard(EventBoardDTO dto) {
		int num = 0; // 글번호 저장 변수
		
		try {
			// 1.2. 디비연결
			con = getCon();
			
			// 3 sql 작성 (글 번호를 계산하는 sql) & pstmt 객체
			sql = "select max(num) from movie_event_board";
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
			sql = "insert into movie_event_board values (?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);  // 직접 계산한 글번호
			pstmt.setString(2, dto.getCategory());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getEventDateStart());
			pstmt.setString(5, dto.getEventDateEnd());
			pstmt.setString(6, dto.getImage());
			pstmt.setString(7, dto.getImage_thumb());
			
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
			sql = "delete from movie_event_board where num=?";
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
	public int updateBoard(EventBoardDTO dto) {
		int result = -1;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성(update) & pstmt 객체
			sql = "update movie_event_board set subject=?, category=?, eventDateStart=?, eventDateEnd=?, image=?, image_thumb=? where num=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getCategory());
			pstmt.setString(3, dto.getEventDateStart());
			pstmt.setString(4, dto.getEventDateEnd());
			pstmt.setString(5, dto.getImage());
			pstmt.setString(6, dto.getImage_thumb());
			pstmt.setInt(7, dto.getNum());
			
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