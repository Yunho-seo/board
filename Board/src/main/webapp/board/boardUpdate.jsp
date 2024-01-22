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
<title> 게시글 수정 페이지 </title>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
 	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
 	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
 	 <style>
 	 .fakeimg {
 	   height: 200px;
	    background: #aaa;
	  }
	  </style>
</head>

<div class="container">
<div class="p-5 bg-primary text-white text-center">
  <h1>서윤호의 게시판</h1>
  <p>Hello!</p> 
</div>

<body>

<form action="boardUpdate.do", method="post">
<input type="hidden" name="num" value="<%=vo.getNum()%>"/>
	<h1>게시글 수정화면</h1>
	<table class="table table-hover">
	<tbody>
	<% if(vo!=null) { %>
		<tr>
			<td>번호</td>
			<td><%=vo.getNum()%></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type='text' name='title' value="<%=vo.getTitle()%>" required/></td>
		</tr>
		<tr>
			<td>본문</td>
			<td><textarea wrap='hard' rows='10' cols='100' name='content' required/><%=vo.getContent()%></textarea>
	    		<br> ※ 최대 500자까지 작성 가능합니다.
	    	</td>
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
	    	<br> ※ 수정하려면 비밀번호를 입력하세요. </td>
	  	</tr>
		<% } else { %>
		<tr>
			<td>일치하는 글이 없습니다.</td>
		</tr>
		<% } %>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="수정" class='btn btn-primary'/>
				<input type="reset" value="취소" class="btn btn-warning" onclick="history.back();"/>
			</td>
		</tr>
	</tbody>
	</table>
</div>
<%!
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(date);
}
%>
</form>
</body>
</html>