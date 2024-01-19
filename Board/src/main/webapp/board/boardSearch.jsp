<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%
	request.setCharacterEncoding("UTF-8");	
	String searchText = request.getParameter("searchText");

	// DB에서 데이터 가져오기
	BoardDAO dao = new BoardDAO();
	ArrayList<BoardVO> list = dao.searchTitle(searchText);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 리스트</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<h1>리스트화면</h1>

<div class="div1">
<form action="/Board/boardSearch.do" name="searchForm" method="post">
	<select name="searchField" id="search">
		<option value="title">제목</option>
		<option value="writer">작성자</option>		
	</select>
	<input type="text" name="searchText" id="searchText">
	<button type="submit">검색</button>
</form>	
</div>

<table class='table table-bordered'>
	<thead>
	<tr>
		<td colspan="5" align="right"><input type="button" value="글 쓰기" class="btn btn-outline-primary" 
	  	onclick="location.href='/Board/board/boardRegister.html'"/></td>
	</tr>
	<tr>
		<th width="15%">글 번호</th>
		<th width="40%">제목</th>
		<th width="15%">작성자</th>
		<th width="15%">작성일자</th>
		<th width="15%">조회수</th>
	</tr>
	</thead>
	<% for(BoardVO vo : list) { %>
			<tr>
			<td><%=vo.getNum()%></td>
			<td><a href="/Board/boardContent.do?num=<%=vo.getNum()%>"><%=vo.getTitle()%></a></td>
			<td><%=vo.getWriter()%></td>
			<td><%=formatDate(vo.getRegdate())%></td>
			<td><%=vo.getRegcount()%></td>
		</tr>
	<% } %>
</table>
<%!
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
}
%>
</body>
</html>