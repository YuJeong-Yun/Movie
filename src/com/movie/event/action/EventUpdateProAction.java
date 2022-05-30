package com.movie.event.action;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.event.db.EventBoardDAO;
import com.movie.event.db.EventBoardDTO;
import com.movie.notice.db.NoticeBoardDAO;
import com.movie.notice.db.NoticeBoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class EventUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : EventUpdateProAction_execute() 호출 ");
		
		// 전달정보 저장
		String pageNum = request.getParameter("pageNum");
		
		// 1) 파일 업로드
		// 		- 가상의 업로드 폴더 설정 : upload 폴더
		String path = request.getRealPath("/upload");
		System.out.println(" M path : " +path);
		//		- 업로드 파일의 크기 설정(제한) - 10MB
		int maxSize = 10 * 1024 * 1024; 
		//		- MultipartRequest 객체 생성(업로드)
		MultipartRequest  multi 
				= new MultipartRequest(
						request,	// request 내장객체 (파라메터, 파일정보)
						path,		// 업로드 위치(가상경로)
						maxSize,		// 업로드 파일의 크기 
						"UTF-8",		// 업로드시 인코딩처리
						new DefaultFileRenamePolicy()		// 중복된 파일이름 업로드시 처리 객체 -> 중복된 이름 넣으면 이름 뒤에 (1) 이런식으로 붙임
						);
		
		System.out.println(" M : 파일 업로드 완료! ");
		
		
		// -------------------------------- 썸네일 새로 생성 ----------------------------
		// 썸네일 이미지 생성 --------------------------------------
		String image = multi.getFilesystemName("image");
		System.out.println(" M : 파일 이름 "+ image);
		
		// ParameterBlock 클래스에 변환할 이미지를 담고 그 이미지를 불러온다.
		ParameterBlock pb=new ParameterBlock();
		pb.add(path+"\\"+image);
		// fileLoad 연산은 JAI가 제공하는 코덱을 사용한다는 옵션
		RenderedOp rOp=JAI.create("fileload",pb);
		
		// 불러온 이미지를 bi로 생성한 BufferedImage 클래스에 담는다 
		BufferedImage bi= rOp.getAsBufferedImage();
		// 이미지 자르기 (X축 시작점, Y축 시작점, 가로 너비, 세로 너비)
		int x = bi.getWidth();
		int y = (int)Math.round(x*0.664);
		BufferedImage new_buff = bi.getSubimage(0, 0, x, y);
		// thumb란 이미지 버퍼를 생성하고 버퍼의 사이즈를 설정 
		BufferedImage thumb=new BufferedImage(282,190,BufferedImage.TYPE_INT_RGB);
		
		// thumb란 이미지 버퍼에 원본 이미지를 정해진 버퍼 사이즈로 맞추어 드로우 
		Graphics2D g=thumb.createGraphics();
		g.drawImage(new_buff,0,0,282,190,null);
		
		// 출력할 위치와 파일 이름을 설정하고 썸네일 이미지 생성 
		// 여기서는 확장자를 jpg로 설정했음 
		File file=new File(path+"/th_"+image);
		ImageIO.write(thumb,"jpg",file);
		//------------------------------------------------------------------------
		
		
		// 2) DB저장
		// 전달정보를 저장 (DTO) -  num, category, subject, eventDateStart, eventDateEnd, image
		int num = Integer.parseInt(multi.getParameter("num"));
		String category = multi.getParameter("category");
		
		EventBoardDTO dto = new EventBoardDTO();
		dto.setNum(num);
		dto.setCategory(category);
		dto.setSubject(multi.getParameter("subject"));
		dto.setEventDateStart(multi.getParameter("eventDateStart"));
		dto.setEventDateEnd(multi.getParameter("eventDateEnd"));
		dto.setImage(image); // 서버에 업로드된 파일명
		dto.setImage_thumb("th_"+image); // 썸네일 파일명
//		dto.setFile(multi.getOriginalFileName("file")); // 실제 파일명
		
		
//		System.out.println(" M  dto : " + dto);
		
		// DAO 객체 생성 - 글 수정 메서드 호출 updateBoard()
		EventBoardDAO dao = new EventBoardDAO();
		// 처리결과 저장 (-1, 0, 1)
		int result = dao.updateBoard(dto);
		
		
		response.setContentType("text/html; charset=UTF-8");
		// 응답결과를 처리하는 연결통로를 지정(데이터 보낼 준비)
		PrintWriter out = response.getWriter();
		
		if(result==-1) { // 글정보 없음
			out.print("<script>");
			out.print("alert('글정보가 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
			
			out.flush();
			out.close();
			
		}else { // result == 1
			out.print("<script>");
			out.print("alert('글 수정을 완료했습니다.');");
			out.print("location.href='./EventContent.ev?num="+num+"&pageNum="+pageNum+"&category="+category+"';");
			out.print("</script>");
			
			out.flush();
			out.close();
			
		}
		return null;
	}

}
