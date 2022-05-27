package com.movie.ticketing.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.movie.member.db.MemberDTO;

public class TicketingDAO {

	// 디비에 movie_board 테이블과 관련된 모든 동작을 처리

	// 공통변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 디비연결 메서드
	private Connection getCon() throws Exception {
		// 1.2. 디비 연결

		// 1) 프로젝트 정보를 초기화
		// Context => 내 프로젝트를 의미
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
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

			System.out.println("DAO : 디비 연결 자원해제!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 디비 자원해제 메서드
	////////////////////////////////////////////////////////////////////

	
	// 주문번호 생성
	public String makeOrderNum() {
		int num = 0;
		String order_num = "";
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체
			sql = "select max(num) from movie_ticketing";
			pstmt = con.prepareStatement(sql);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				num = rs.getInt(1)+1;
			}
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			order_num = sdf.format(new Date()) + "-" + String.format("%06d", num);
			
			System.out.println("DAO : 주문번호 생성 완료 "+order_num);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return order_num;
	} // makeOrderNum
	
	
	// 회원 정보 가져오기
	public MemberDTO getMemberInfo(String id) {
		MemberDTO dto = null;
				
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체
			sql = "select * from movie_member where id=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, id);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				dto = new MemberDTO();
				
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
			}
			
			System.out.println("DAO : 회원 정보 조회 완료! "+dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return dto;
	} // getMemberInfo()
	
	
	// 결제정보 DB에 저장
	public void insertPayInfo(TicketingDTO dto) {
		int num = 0;
		
		try {
			// 1.2. 결제 연결
			con = getCon();
			
			/////////////////////////////////////// num 계산하기
			// 3. sql 작성 & pstmt 객체
			sql = "select max(num) from movie_ticketing";
			pstmt = con.prepareStatement(sql);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				num = rs.getInt(1)+1;
			}
			System.out.println("DAO : num 계산 "+num);
			
			//////////////////////////////////////// 결제 정보 insert
			// 3. sql 작성 & pstmt 객체
			sql = "insert into movie_ticketing(num, order_num, member_id, movie_title, movie_theater, movie_dateTime, movie_seat, order_date, price, payment_num) "
					+ "values(?,?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getOrder_num());
			pstmt.setString(3, dto.getMember_id());
			pstmt.setString(4, dto.getMovie_title());
			pstmt.setString(5, dto.getMovie_theater());
			pstmt.setString(6, dto.getMovie_dateTime());
			pstmt.setString(7, dto.getMovie_seat());
			pstmt.setInt(8, dto.getPrice());
			pstmt.setString(9, dto.getPayment_num());
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO : 정보 저장 완료 "+dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	} //insertPayInfo
	
	
	// 예매 내역 조회
	public List<TicketingDTO> getPayInfo(String id) {
		List<TicketingDTO> payList = new ArrayList<>();
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체
			sql = "select * from movie_ticketing where member_id=? order by num desc";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, id);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			while(rs.next()) {
				TicketingDTO dto = new TicketingDTO();
				
				dto.setMember_id(id);
				dto.setMovie_dateTime(rs.getString("movie_dateTime"));
				dto.setMovie_seat(rs.getString("movie_seat"));
				dto.setMovie_theater(rs.getString("movie_theater"));
				dto.setMovie_title(rs.getString("movie_title"));
				dto.setNum(rs.getInt("num"));
				dto.setOrder_date(rs.getTimestamp("order_date"));
				dto.setOrder_num(rs.getString("order_num"));
				dto.setPayment_num(rs.getString("payment_num"));
				dto.setPrice(rs.getInt("price"));

				payList.add(dto);
			}
			System.out.println("DAO : 결제정보 저장 완료 "+payList);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return payList;
	} // getPayInfo()
	
}
