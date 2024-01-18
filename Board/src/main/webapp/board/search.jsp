<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.*"; %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>

<tbody>
	<%
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = dao.getSearch(request.getParameter("searchField"),
				request.getParameter("searchText"));
		
		if(list.size() == 0) {  // 검색 결과가 없다면
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('검색결과가 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		for (int i=0; i<list.size(); i++) {
	%>
	<%--
	<tr>
		<td><%=list.get(i).getNum()%></td>
		<%-- 현재 게시글에 대한 정보
		<td><a href="view.jsp?bbsID=<%=list.get(i).getBbsID()%>"><%=list.get(i).getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
			.replaceAll(">", "&gt;").replaceAll("\n", "<br>")%></a></td>
		<td><%=list.get(i).getUserID()%></td>
		<td><%=list.get(i).getBbsDate().substring(0, 11) + list.get(i).getBbsDate().substring(11, 13) + "시"
			+ list.get(i).getBbsDate().substring(14, 16) + "분"%></td>
		<td><%=list.get(i).getBbsCount()%></td>
		<td><%=list.get(i).getLikeCount()%></td>
	</tr>
	--%>
	
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

</tbody>