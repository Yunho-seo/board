package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardVO;

@WebServlet("/boardList.do")
public class BoardListController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int pageNumber = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		int pageSize = 5;
		
		BoardDAO dao = new BoardDAO();
		// ArrayList<BoardVO> list = dao.boardList();
		ArrayList<BoardVO> list = dao.selectPage(pageNumber, pageSize);
		
		int totalRecords = dao.getTotalRecords();
		int totalPages = (int)Math.ceil((double) totalRecords / pageSize);
		
		request.setAttribute("list", list);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("totalPages", totalPages);
		
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
		rd.forward(request, response);
	}
}