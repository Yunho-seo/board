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

@WebServlet("/boardSearch.do")
public class BoardSearchController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
        String searchText = request.getParameter("searchText");
        
		BoardDAO dao = new BoardDAO();	
		ArrayList<BoardVO> list = dao.searchTitle(searchText);
		// ArrayList<BoardVO> writerList = dao.searchWriter(searchText);
		
        request.setAttribute("list", list);
        RequestDispatcher rd = request.getRequestDispatcher("/board/boardSearch.jsp");
        rd.forward(request, response);
	}
}