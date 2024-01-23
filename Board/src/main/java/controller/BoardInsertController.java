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

@WebServlet("/boardInsert.do")
public class BoardInsertController extends HttpServlet {	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		String pw = request.getParameter("pw");
		
		if (title.length() > 30) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('제목은 30자 이하로 입력해주세요.');history.go(-1);</script>");
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
		} else if (writer.length() > 20) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('작성자명은 20자 이하로 입력해주세요.');history.go(-1);</script>");
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
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		vo.setPw(pw);
		
		// System.out.println(vo.toString());
		BoardDAO dao = new BoardDAO();
		int cnt = dao.boardInsert(vo);
		if (cnt > 0) {
			response.sendRedirect("/Board/boardList.do");
		} else {
			throw new ServletException("not insert");
		}
	}
}