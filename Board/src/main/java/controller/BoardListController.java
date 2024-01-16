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
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = dao.boardList();
		
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
		rd.forward(request, response);
	}

}
