package controller;

import java.io.IOException;

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
		
		BoardVO vo = new BoardVO();
		vo.setNum(num);
		vo.setTitle(title);
		vo.setContent(content);
		
		BoardDAO dao = new BoardDAO();
		int cnt = dao.boardUpdate(vo);
		
		if (cnt > 0) {
			response.sendRedirect("/Board/boardList.do");
		} else {
			throw new ServletException("not update");
		}
		
	}
}