package com.movie.board.action.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MovieBoardDAO {
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
			sql = "select count(num) from movie_board";
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
	public ArrayList<MovieBoardDTO> getBoardList(int startRow, int pageSize) {
		ArrayList<MovieBoardDTO> boardList = new ArrayList<>();
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체 (num 내림차순 정렬, 페이징 처리)
			//		=> limit 시작행(0부터 시작함), 개수
			sql = "select * from (select @ROWNUM:=@ROWNUM +1 AS rownum, T.*	FROM movie_board T, (select @ROWNUM:=0) R "
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
				// 글 1개의 정보 => BoardBean 객체
				// BoardBean 객체의 정보를 ArrayList 한 칸에 저장
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
				
				boardList.add(dto);
			}
			System.out.println("DAO : 게시판 글 전체 목록 저장완료!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return boardList;
	} // getBoardList
	
	
	// 글 조회수 증가시키는 메서드
	public int updateReadCount(int num, String id) {
		int result = 0;
		
		try {
			// 1.2 디비연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 생성
			// 글 작성자 아니면 기존 조회수 + 1 구문
			if(id == null) {
				sql = "update movie_board set readcount = readcount + 1 where num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
			}else {
				sql = "update movie_board set readcount = readcount + 1 where num = ? and id != ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, id);
			}
			
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
	public MovieBoardDTO getBoard(int num) {
		MovieBoardDTO dto = null;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체
			sql = "select * from movie_board where num = ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 조회
			if(rs.next()) {
				dto = new MovieBoardDTO();
				
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setId(rs.getString("id"));
				dto.setIp(rs.getString("ip"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_cnt(rs.getInt("re_cnt"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
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
			sql = (i==0) ? "select min(num) from movie_board where num > ?" : "select max(num) from movie_board where num < ?" ;
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				result = (i==0) ? rs.getInt(1) : rs.getInt(1);
			}
			System.out.println("DAO : 글 번호 " + result + " 반환");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	} // getBoardSideNum
	
	
	// 글쓰기 메서드
	public int insertBoard(MovieBoardDTO dto) {
		// 글번호 저장 변수
		int num = 0;
		try {
			// 1.2. 디비연결
			con = getCon();
			
			// 3 sql 작성 (글 번호를 계산하는 sql) & pstmt 객체
			sql = "select max(num) from movie_board";
			pstmt = con.prepareStatement(sql);

			// 4 sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			// max(num) 컬럼의 결과는 항상 존재함 -> 따라서 값이 안들어있어도 rs.next() 결과값이 false가 아님!
			if(rs.next()) {  
				// getInt 메서드는 null값일 경우 0을 반환
				num = rs.getInt(1)+1;   			// 인덱스
			}
			
			System.out.println("DAO  : 글번호 " + num);
			
			/////////////////////////////////////////////////////////
			// 현재 아이디 이름 정보 가져오기
			String name ="";
			
			// 3. sql 작성 & pstmt
			sql = "select name from movie_member where id=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, dto.getId());
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				name = rs.getString("name");
			}
			
			System.out.println("DAO : 이름 " + name);
			
			////////////////////////////////////////////////////////////
			// 글쓰기
			
			// 3. sql(insert) 작성 & pstmt 객체
			sql = "insert into movie_board values (?, ?, ?, ?, ?, ?, ?, now(), ?)";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);  // 직접 계산한 글번호
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, name);
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, 0); // readcount	(생성된 모든 글의 조회수는 0)
			pstmt.setInt(7, 0); // 댓글수 처음엔 0
			pstmt.setString(8, dto.getIp());
			
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
	public int deleteBoard(String id, int num) {
		int result = -1;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체
			sql = "select id from movie_board where num=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				if(id.equals(rs.getString("id"))) { // 아이디 일치 => 글 삭제
					// 3. sql 작성 & pstmt 객체
					sql = "delete from movie_board where num=?";
					pstmt = con.prepareStatement(sql);
					// ???
					pstmt.setInt(1, num);
					
					// 4. sql 실행
					// pstmt.executeUpdate()는 실행된 row 수를 반환함
					 result = pstmt.executeUpdate(); 
				
				} else { // 아이디 다름
					result = 0;
				}
				
			} else { // 글 존재 X
				result = -1;
			} // if
			
			System.out.println(" DAO : 게시판 글 삭제 완료! " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	} // deleteBoard
	
	
//	
//	
//	// getBoardList()
//	public ArrayList getBoardList(){
//		ArrayList boardList = new ArrayList();
//		
//		try {
//			// 1.2. 디비연결
//			con = getCon();
//			// 3. sql 작성 & pstmt 객체 (num 내림차순 정렬)
////				sql = "select * from itwill_board order by num desc";
//			sql = "select * from itwill_board order by num desc limit 0,5";
//			pstmt = con.prepareStatement(sql);
//			// 4. sql 실행
//			rs = pstmt.executeQuery();
//			// 5. 데이터 처리	
//			while(rs.next()){ // 데이터 있을때 DB정보를 모두 저장
//				// 글 1개의 정보 => BoardBean 객체
//				BoardDTO bb = new BoardDTO();
//				
//				bb.setContent(rs.getString("content"));
//				bb.setDate(rs.getDate("date"));
//				bb.setFile(rs.getString("file"));
//				bb.setIp(rs.getString("ip"));
//				bb.setName(rs.getString("name"));
//				bb.setNum(rs.getInt("num"));
//				bb.setPass(rs.getString("pass"));
//				bb.setRe_lev(rs.getInt("re_lev"));
//				bb.setRe_ref(rs.getInt("re_ref"));
//				bb.setRe_seq(rs.getInt("re_seq"));
//				bb.setReadcount(rs.getInt("readcount"));
//				bb.setSubject(rs.getString("subject"));
//				
//				// BoardBean 객체의 정보 => ArrayList 한칸에 저장
//				
//				boardList.add(bb);
//			}// while
//			
//			System.out.println(" DAO : 게시판 글 전체 목록 저장완료! ");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeDB();
//		}
//		
//		return boardList;
//	} // getBoardList()
//	
//		
//		
//		// 수정 정보 DB에 업데이트하는 메서드
//		public int updateBoard(BoardDTO ubb) {
//			int result = -1;
//			
//			try {
//				// 1.2. 디비 연결
//				con = getCon();
//				
//				// 3. sql 작성 & pstmt 객체
//				// 글이 존재하는지 체크
//				sql ="select pass from itwill_board where num = ?";
//				pstmt = con.prepareStatement(sql);
//				// ???
//				pstmt.setInt(1, ubb.getNum());
//				
//				// 4. sql 실행
//				rs = pstmt.executeQuery();
//				
//				// 5. 데이터 처리
//				// 비밀번호가 존재한다 => 게시판 글이 있음!
//				if(rs.next()) {
//					// 글을 쓴 사람일 때만 수정 가능하도록 함
//					// 비밀번호 일치 => 본인임!
//					if(ubb.getPass().equals(rs.getString("pass"))) {
//						// 3. sql 작성(update) & pstmt 객체
//						sql = "update itwill_board set name=?, subject=?, content=? where num=?";
//						pstmt = con.prepareStatement(sql);
//						// ???
//						pstmt.setString(1, ubb.getName());;
//						pstmt.setString(2, ubb.getSubject());
//						pstmt.setString(3, ubb.getContent());
//						pstmt.setInt(4, ubb.getNum());
//						
//						// 4. sql 실행
//						// result = 1과 같음;
//						pstmt.executeUpdate();
//						result = 1;		
//						
//					// 비밀번호 오류 => 본인 아님!
//					} else {
//						result = 0;
//					}
//					
//				} else {
//					result = -1;
//				}
//				System.out.println(" DAO : 정보 수정 완료("+result+")");
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				closeDB();
//			}
//			return result;
//		} // updateBoard 
//		
//		
//		
//		
//		// 답글쓰는 메서드
//		public void reInsertBoard(BoardDTO reBB) {
//			int num = 0;
//			
//			try {
//				// 답글 번호 계산 --------------------------------------------------------------------
//				// 1.2. 디비 연결
//				con = getCon();
//				
//				// 3. sql 작성 & pstmt 객체
//				sql = "select max(num) from itwill_board";
//				pstmt = con.prepareStatement(sql);
//				
//				// 4. sql 실행
//				rs = pstmt.executeQuery();
//				
//				// 5. 데이터 처리
//				if(rs.next()) {
//					num = rs.getInt("max(num)")+1;
//				}
//				
//
//				
//				// 답글 순서 재배치 (기존의 답글) ----------------------------------------------------
//				// 3. sql 구문 & pstmt 객체
//				//    답글 중에서 seq 값이 동일한 값이 있을 때 그 값들 1증가
//				//    re_ref(같은 그룹), re_seq(순서)가 기존(부모글)의 값보다 큰 값이 있을 떄
//				sql = "update itwill_board set re_seq = re_seq + 1 where re_ref=? and re_seq>?";
//				pstmt = con.prepareStatement(sql);
//				// ???
//				pstmt.setInt(1, reBB.getRe_ref());
//				pstmt.setInt(2, reBB.getRe_seq());
//				
//				// 4. sql 실행
//				int check = pstmt.executeUpdate();
//				
//				if(check > 0) {
//					System.out.println("  DAO : 답글 순서 재배치 완료!");
//				}
//				
//				
//				
//				// 답글 저장 (ref-부모글의 값, lev-부모+1, seq-부모+1) -------------------------------
//				// 3. sql 작성 & pstmt 객체
//				sql="insert into itwill_board(num, name, pass, subject, content, readcount, "
//						+ "re_ref, re_lev, re_seq, date,ip,file) values(?,?,?,?,?,?,?,?,?,now(),?,?)";
//				pstmt = con.prepareStatement(sql);
//				// ???
//				pstmt.setInt(1, num);
//				pstmt.setString(2, reBB.getName());
//				pstmt.setString(3, reBB.getPass());
//				pstmt.setString(4, reBB.getSubject());
//				pstmt.setString(5, reBB.getContent());
//				pstmt.setInt(6, 0); // 조회수 0
//				pstmt.setInt(7, reBB.getRe_ref()); // ref = 부모글의 번호
//				pstmt.setInt(8, reBB.getRe_lev()+1); // lev = 부모글 + 1
//				pstmt.setInt(9, reBB.getRe_seq()+1); // seq = 부모글 + 1
//				pstmt.setString(10, reBB.getIp());
//				pstmt.setString(11, reBB.getFile());
//				
//				// 4. sql 실행
//				pstmt.executeUpdate();
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				closeDB();
//			}
//		} // reWrite
//		
//		
//		
//		
//		// 검색어 포함된 글 개수 반환 getBoardCount() - 오버로딩
//		public int getBoardCount(String search) {
//			int searchCnt = 0;
//			
//			try {
//				// 1.2. 디비연결
//				con = getCon();
//				
//				// 3. sql 작성(select) & pstmt 객체
//				sql = "select count(*) from itwill_board where subject like ?";
//				pstmt = con.prepareStatement(sql);
//				// ???
//				pstmt.setString(1, "%"+search+"%"); // like에 ' ' 안 써도 됨!!
//				
//				// 4. sql 실행
//				rs = pstmt.executeQuery();
//				
//				// 5. 데이터 처리
//				if(rs.next()) {
//					searchCnt = rs.getInt(1);
//					// searchCnt = rs.getInt("count(*); // 컬럼명
//				}
//				
//				System.out.println("DAO : 검색된 글개수 " + searchCnt + "개");
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				closeDB();
//			}
//			return searchCnt;
//		} // getBoardCount() - 오버로딩
//		
//		
//		
//		// 검색어 결과목록 가져오는 메서드 - 오버로딩
//		public List<BoardDTO> getBoardList(int startRow, int pageSize, String search) {
//			List<BoardDTO> searchBoardList = new ArrayList<BoardDTO>();
//			
//			try {
//				// 1.2. 디비 연결
//				con = getCon();
//				
//				// 3. sql 작성 & pstmt 객체
//				// 정렬- re_ref (내림차순), re_seq (오름차순)
//				// limit - 원하는 만큼만 가져오기
//				// 검색어 포함글만 select 
//				sql = "select * from itwill_board where subject like ? order by re_ref desc, re_seq asc limit ?, ?";
//				pstmt = con.prepareStatement(sql);
//				// ???
//				pstmt.setString(1, "%"+search+"%");
//				pstmt.setInt(2, startRow-1);
//				pstmt.setInt(3, pageSize);
//				
//				// 4. sql 실행
//				rs = pstmt.executeQuery();
//				
//				// 5. 데이터 처리 (list에 저장)
//				while(rs.next()) {
//					// BoardBean 객체의 정보를 ArrayList 한 칸에 저장
//					BoardDTO bb = new BoardDTO();
//					
//					bb.setContent(rs.getString("content"));
//					bb.setDate(rs.getDate("date"));
//					bb.setFile(rs.getString("file"));
//					bb.setIp(rs.getString("ip"));
//					bb.setName(rs.getString("name"));
//					bb.setNum(rs.getInt("num"));
//					bb.setPass(rs.getString("pass"));
//					bb.setRe_lev(rs.getInt("re_lev"));
//					bb.setRe_ref(rs.getInt("re_ref"));
//					bb.setRe_seq(rs.getInt("re_seq"));
//					bb.setReadcount(rs.getInt("readcount"));
//					bb.setSubject(rs.getString("subject"));
//					
//					// DTO -> arrayList 한 칸에 저장
//					searchBoardList.add(bb);
//				}
//				System.out.println("DAO : 게시판 글 전체 목록 저장완료!");
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				closeDB();
//			}
//			return searchBoardList;
//			
//		} // getBoardList - 오버로딩
//		
		
}

