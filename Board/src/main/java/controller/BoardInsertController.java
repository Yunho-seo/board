package controller;

import java.io.IOException;

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
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		
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