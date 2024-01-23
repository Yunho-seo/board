<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%
	request.setCharacterEncoding("UTF-8");

	// DB에서 데이터 가져오기
	BoardDAO dao = new BoardDAO();	
		ArrayList<BoardVO> list = dao.getSearch(request.getParameter("searchField"), 
				request.getParameter("searchText"));
%>
<!DOCTYPE html>
<html>
<head>
  <title>서윤호의 게시판</title>
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

<body>
<div class="container">
<div class="p-5 bg-primary text-white text-center">
  <h1>서윤호의 게시판</h1>
  <p>Hello!</p> 
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <ul class="navbar-nav">
    <li class="nav-item">
        <a class="nav-link" href="/Board/boardList.do">메인으로</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">자유게시판</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">공지사항</a>
      </li>
    </ul>
  </div>
</nav>

<div class="div1">
<form action="/Board/boardSearch.do" name="searchForm" method="post">
	<table class="pull-right">
	<tr>
		<td>
			<select class="form-control" name="searchField">
				<option value="title">제목 ▼</option>
				<option value="writer">작성자</option>		
			</select>
		</td>
		<td><input type="text" class="form-control"
			 placeholder="검색어 입력" name="searchText">
		</td>
		<td>
			<button type="submit" class="btn btn-outline-primary">검색</button>
		</td>
	</tr>
	</table>
</form>	
</div>

<div class="div2" align="right">
<tr>
	<input type="button" value="게시글 쓰기" class="btn btn-outline-primary" 
	  onclick="location.href='/Board/board/boardRegister.html'"/></td>
</tr>
</div>

<table class='table table-hover'>
	<thead>
	
	<tr>
		<th width="15%">글 번호</th>
		<th width="40%">제목</th>
		<th width="15%">작성자</th>
		<th width="15%">작성일자</th>
		<th width="15%">조회수</th>
	</tr>
	</thead>
	<tbody>
	<% for(BoardVO vo : list) { %>
			<tr>
			<td><%=vo.getNum()%></td>
			<td><a href="/Board/boardContent.do?num=<%=vo.getNum()%>"><%=vo.getTitle()%></a></td>
			<td><%=vo.getWriter()%></td>
			<td><%=formatDate(vo.getRegdate())%></td>
			<td><%=vo.getRegcount()%></td>
		</tr>
	<% } %>
	</tbody>
</table>


<%!
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
}
%>