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
<title>게시글 수정화면</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

	<script type="text/javascript">
	function deleteFn(num) {
		location.href="boardDelete.do?num="+num;
	}
	</script>

</head>
<body>
<h1>게시글삭제화면</h1>

<table class="table table-bordered">
<% if(vo!=null) { %>
	<tr>
		<td>번호</td>
		<td><%=vo.getNum()%></td>
	</tr>
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
    	<td>비밀번호</td>
    	<td><input type="password" name="pw" required/>
    	<br> ※ 삭제하려면 비밀번호를 입력하세요. </td>
  	</tr>
	<% } else { %>
	<tr>
		<td>일치하는 글이 없습니다.</td>
	</tr>
	<% } %>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="삭제" class='btn btn-primary'/>
			<input type="reset" value="취소" class="btn btn-warning" onclick="history.back();"/>
		</td>
	</tr>
</table>
<%!
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(date);
}
%>
</body>
</html>