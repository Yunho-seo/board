<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%
	// DB에서 데이터 가져오기
	BoardDAO dao = new BoardDAO();
	ArrayList<BoardVO> list = dao.boardList();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트화면</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<h1>리스트화면</h1>

<table class='table table-bordered'>
	<thead>
	<tr>
		<td colspan="5" align="laft"><input type="button" value="글 쓰기" class="btn btn-outline-primary" 
	  	onclick="location.href='/Board/board/boardRegister.html'"/></td>
	</tr>
	<tr>
		<td>글 번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일자</td>
		<td>조회수</td>
	</tr>
	</thead>
	<% for(BoardVO vo : list) { %>
			<tr>
			<td><%=vo.getNum()%></td>
			<td><a href="/Board/boardContent.do?num=<%=vo.getNum()%>"><%=vo.getTitle()%></a></td>
			<td><%=vo.getWriter()%></td>
			<td><%=formatDate(vo.getRegdate())%></td>
			<td><%=vo.getRegcount()%></td>
			<!-- <td><input type="button" value="삭제" class="btn btn-warning" onclick="deleteFn(<%=vo.getNum()%>)"></td> -->
		</tr>
	<% } %>
</table>

<div class="high">
	<form action="search.jsp" name="search" method="post">
		<table class="pull-right">
			<tr>
				<td> <select name="searchField" class="form-control">
						<option value="0">선택</option>
						<option value="Title">제목</option>
						<option value="Writer">작성자</option>
					</select></td>
					<td><input type="text" class="form-control"
						placeholder="검색어 입력" name="searchText" maxlength="100"></td>
					<td><button type="submit" class="btn btn-success">검색</button></td>
			</tr>	
		</table>	
	</form>
</div>
<%!
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
}
%>
</body>
</html>