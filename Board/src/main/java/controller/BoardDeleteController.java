package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;

@WebServlet("/boardDelete.do")
public class BoardDeleteController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pw = request.getParameter("pw");
		
		BoardDAO dao = new BoardDAO();
		
		boolean checkPw = dao.checkPw(num, pw);
		
		if (!checkPw) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 일치하지 않습니다.');history.go(-1);</script>");
			out.flush();  // 버퍼에 있는 것을 실제 모니터, 콘솔에 출력
			out.close();  // 파일 닫기
			return;
		}
		
		// delete Logic
		int cnt = dao.boardDelete(num);
		
		if (cnt > 0) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('삭제가 완료되었습니다.');location.href='/Board/boardList.do';</script>");
            out.flush();  // 버퍼에 있는 것을 실제 모니터, 콘솔에 출력
            out.close();  // 파일 닫기
		} else {
			throw new ServletException("not delete");
		}
	}
}