package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardVO;

@WebServlet("/boardUpdate.do")
public class BoardUpdateController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pw = request.getParameter("pw");
		
		BoardDAO dao = new BoardDAO();
		
		if (title.length() > 30) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('제목은 20자 이하로 입력해주세요.');history.go(-1);</script>");
			out.flush();
            out.close();
            return;
		} else if (content.length() > 500) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('본문은 500자 이하로 입력해주세요.');history.go(-1);</script>");
			out.flush();
            out.close();
            return;
		} else if (pw.length() > 10) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호는 10자 이하로 입력해주세요.');history.go(-1);</script>");
			out.flush();
            out.close();
            return;
		}
		
		boolean checkPw = dao.checkPw(num, pw);
		
		if (!checkPw) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 일치하지 않습니다.');history.go(-1);</script>");
			out.flush();  // 버퍼에 있는 것을 실제 모니터, 콘솔에 출력
            out.close();  // 파일 닫기
            return;
		}
		
		// Update Logic
		BoardVO vo = new BoardVO();
		vo.setNum(num);
		vo.setTitle(title);
		vo.setContent(content);
	
		int cnt = dao.boardUpdate(vo);
		
		if (cnt > 0) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('수정이 완료되었습니다.');location.href='/Board/boardList.do';</script>");
            out.flush();  // 버퍼에 있는 것을 실제 모니터, 콘솔에 출력
            out.close();  // 파일 닫기
		} else {
			throw new ServletException("not update");
		}
	}
}