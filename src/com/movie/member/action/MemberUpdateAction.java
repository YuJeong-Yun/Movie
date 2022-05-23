package com.movie.member.action;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.member.db.MemberDAO;
import com.movie.member.db.MemberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberUpdateAction_execute() 실행");
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
		
			
			//  파일 업로드
			String path = request.getRealPath("/profile");
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
			String image = multi.getFilesystemName("profile");
			System.out.println(" M : 파일 이름 "+ image);
			String th_profile = null;
			
			// 파일 업로드 했을 경우 실행
			if(image != null) {
				// ParameterBlock 클래스에 변환할 이미지를 담고 그 이미지를 불러온다.
				ParameterBlock pb=new ParameterBlock();
				pb.add(path+"\\"+image);
				// fileLoad 연산은 JAI가 제공하는 코덱을 사용한다는 옵션
				RenderedOp rOp=JAI.create("fileload",pb);
				
				// 불러온 이미지를 bi로 생성한 BufferedImage 클래스에 담는다 
				BufferedImage bi= rOp.getAsBufferedImage();
				// thumb란 이미지 버퍼를 생성하고 버퍼의 사이즈를 설정 
				BufferedImage thumb=new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
				
				// thumb란 이미지 버퍼에 원본 이미지를 정해진 버퍼 사이즈로 맞추어 드로우 
				Graphics2D g=thumb.createGraphics();
				g.drawImage(bi,0,0,100,100,null);
				
				// 출력할 위치와 파일 이름을 설정하고 썸네일 이미지 생성 
				// 여기서는 확장자를 jpg로 설정했음 
				File file=new File(path+"/th_"+id+".jpg");
				ImageIO.write(thumb,"jpg",file);
				
				th_profile = "th_"+id;
				//---------------------------------------------------------
			}
			
			
		
			// 전달정보 저장
			String addr = multi.getParameter("postcode")+ "/"+multi.getParameter("address")+"/"
					+multi.getParameter("detailAddress") +"/"+ multi.getParameter("extraAddress");
			
			int profileDel = 0;
			// profileDel != null이면 profileDel = 1 => 프로필 삭제
			if(multi.getParameter("profileDel") == null ) {
			}else { // 업로드한 프로필 썸네일 파일 삭제
				profileDel = 1;
				
				File file = new File(path+"/th_"+id+".jpg");
		        if(file.exists()) {    //삭제하고자 하는 파일이 해당 서버에 존재하면 삭제시킨다
		            file.delete();
		        }
			}
			System.out.println("pro "+profileDel);
			
			MemberDTO dto = new MemberDTO();
			dto.setAddr(addr);
			dto.setEmail(multi.getParameter("email"));
			dto.setGender(multi.getParameter("gender"));
			dto.setTel(multi.getParameter("tel"));
			dto.setPw(multi.getParameter("pw"));
			dto.setId(id);
			dto.setProfile(multi.getFilesystemName("profile"));
			dto.setTh_profile(th_profile);
			
			// DAO 객체
			MemberDAO dao = new MemberDAO();
			int result = dao.updateMember(dto, profileDel); // -1:회원 없음, 0:비밀번호 오류, 1:정보 수정
	
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(result==-1) { // 아이디 없음
				out.println("<script>");
				out.println("alert('회원이 아닙니다.');");
				out.println("location.href='./MyPage.me';");
				out.println("</script>");
				
				out.flush();
				out.close();
				
			}else if(result==0) { // 비밀번호 오류
				out.println("<script>");
				out.println("alert('비밀번호 오류입니다.');");
				out.println("location.href='./MyPage.me';");
				out.println("</script>");
				out.flush();
				out.close();
				
			}else { // 정보 수정 완료
				out.println("<script>");
				out.println("alert('수정이 완료되었습니다.');");
				out.println("location.href='./MyPage.me';");
				out.println("</script>");
				
				out.flush();
				out.close();
				
			}
			
			return null;
	}
}

		
		
	