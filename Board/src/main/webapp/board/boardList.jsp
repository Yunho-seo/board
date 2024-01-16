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
<title>Insert title here</title>
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
		<td colspan="5" align="laft"><input type="button" value="게시글 작성" class="btn btn-primary" 
	  	onclick="location.href='boardRegister.html'"/></td>
	</tr>
	<tr>
		<td>글 번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성시간</td>
		<td>조회수</td>
	</tr>
	</thead>
	<% for(BoardVO vo : list) { %>
			<tr>
			<td><%=vo.getNum()%></td>
			<td><a href=""><%=vo.getTitle()%></a></td>
			<td><%=vo.getWriter()%></td>
			<td><%=formatDate(vo.getRegdate())%></td>
			<td><%=vo.getCount()%></td>
			<!-- <td><input type="button" value="삭제" class="btn btn-warning" onclick="deleteFn(<%=vo.getNum()%>)"></td> -->
		</tr>
	<% } %>
</table>
<%!
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(date);
}
%>
</body>
</html>