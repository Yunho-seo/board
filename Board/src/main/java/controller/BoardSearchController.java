package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
		
		BoardDAO dao = new BoardDAO();	
		ArrayList<BoardVO> list = dao.getSearch(request.getParameter("searchField"), 
				request.getParameter("searchText"));
		
		if (list.size() == 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('검색결과가 없습니다.');history.go(-1);</script>");
			out.flush();
            out.close();
            return;
		}
		
        request.setAttribute("list", list);
        RequestDispatcher rd = request.getRequestDispatcher("/board/boardSearch.jsp");
        rd.forward(request, response);
	}
}