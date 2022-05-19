package com.movie.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.event.db.EventBoardDAO;
import com.movie.event.db.EventBoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import java.awt.Graphics2D;
import java.awt.image.renderable.ParameterBlock;
import java.awt.image.BufferedImage;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.imageio.ImageIO;

import java.util.*;
import java.io.*;

public class EventWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : EventWriteAction_execute() 호출");
		
		// 관리자 계정 아닐경우 이벤트 게시판으로 이동
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null || !id.equals("admin")) {
			forward.setPath("./Event.ev");
			forward.setRedirect(true);
			return forward;
		}
		
		
		
		// 1) 파일 업로드- 가상의 업로드 폴더 설정 : upload 폴더
		String path = request.getRealPath("upload");
		System.out.println(" M path : " +path);
		//		- 업로드 파일의 크기 설정(제한) - 10MB
		int maxSize = 10 * 1024 * 1024; 
		
		//		- MultipartRequest 객체 생성(업로드)
		MultipartRequest multi;
			multi = new MultipartRequest(
					request,	// request 내장객체 (파라메터, 파일정보)
					path,		// 업로드 위치(가상경로)
					maxSize,		// 업로드 파일의 크기 
					"UTF-8",		// 업로드시 인코딩처리
					new DefaultFileRenamePolicy()		// 중복된 파일이름 업로드시 처리 객체 -> 중복된 이름 넣으면 이름 뒤에 (1) 이런식으로 붙임
					);
		
		System.out.println(" M : 파일 업로드 완료! ");
		
		
		
		
		// 썸네일 이미지 생성 --------------------------------------
		String image = multi.getFilesystemName("image");
		
		// ParameterBlock 클래스에 변환할 이미지를 담고 그 이미지를 불러온다.
		ParameterBlock pb=new ParameterBlock();
		pb.add(path+"\\"+image);
		// fileLoad 연산은 JAI가 제공하는 코덱을 사용한다는 옵션
		RenderedOp rOp=JAI.create("fileload",pb);
		
		// 불러온 이미지를 bi로 생성한 BufferedImage 클래스에 담는다 
		BufferedImage bi= rOp.getAsBufferedImage();
		// thumb란 이미지 버퍼를 생성하고 버퍼의 사이즈를 100*100으로 설정 
		BufferedImage thumb=new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
		
		// thumb란 이미지 버퍼에 원본 이미지를 정해진 버퍼 사이즈인 100*100으로 맞추어 드로우 
		Graphics2D g=thumb.createGraphics();
		g.drawImage(bi,0,0,282,190,null);
		
		// 출력할 위치와 파일 이름을 설정하고 썸네일 이미지 생성 
		// 여기서는 확장자를 jpg로 설정했음 
		File file=new File(path+"/th_"+image);
		ImageIO.write(thumb,"jpg",file);
		//---------------------------------------------------------
		
		
		
		// 2) DB저장
		String category = multi.getParameter("category"); // 파라메터로 전달할 변수
		
		EventBoardDTO dto = new EventBoardDTO();
		
		dto.setSubject(multi.getParameter("subject"));
		dto.setCategory(category);
		dto.setEventDateStart(multi.getParameter("eventDateStart"));
		dto.setEventDateEnd(multi.getParameter("eventDateEnd"));
		dto.setImage(image); // 서버에 업로드된 이미지명
		dto.setImage_thumb("th_"+image); // 생성한 섬네일 이미지
		
		System.out.println(" M  dto : " + dto);
		
		//		- DAO 객체 생성 - 업로드 메서드 호출
		EventBoardDAO dao = new EventBoardDAO();
		int num = dao.insertBoard(dto);
		
		// 페이지 이동 
		forward.setPath("./EventContent.ev?num="+num+"&pageNum=1&category="+category);
		forward.setRedirect(true);
		return forward;
	}

}
