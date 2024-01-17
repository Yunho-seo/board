<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%
	BoardVO vo = (BoardVO)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자세히보기</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
	<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>
	<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js'></script>
	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>
</head>
<body>
<form action="boardUpdate.do" method="post">  <!-- 수정하기 버튼 누르면 boardUpdate.jsp 페이지로 action -->

<table class='table table-bordered'>
<% if(vo!=null) { %>
	<tr>
		<td>제목</td>
		<td><%=vo.getTitle()%></td>
	</tr>
	<tr>
		<td>본문</td>
		<td><%=vo.getContent()%></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><%=vo.getWriter()%></td>
	</tr>
	<tr>
		<td>등록일자</td>
		<td><%=formatDate(vo.getRegdate())%></td>
	</tr>
	<tr>
		<td>조회수</td>
		<td><%=vo.getRegcount()%></td>
	</tr>
	<% } else { %>
	<tr>
		<td>일치하는 글이 없습니다.</td>
	</tr>
	<% } %>
	<tr>
		<td colspan="2" align="right">
			<input type="submit" value="수정" class='btn btn-primary'/>
			<input type="delete" value="삭제" class='btn btn-warning'/>
			<input type="button" value="돌아가기" onclick="location.href='/Board/board/boardList.jsp'" class='btn'/>
		</td>
	</tr>
</table>
<%!
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(date);
}
%>
</form>
</body>
</html>