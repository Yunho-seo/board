package model;

import java.sql.*;
import java.util.*;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public void getConnect() {
		String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimeZone=UTC";
		String user = "root";
		String password = "admin12345";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// limit을 통해 페이지 뿌려줌
	public ArrayList<BoardVO> selectPage(int pageNumber, int pageSize) {
		String SQL = "select num, title, writer, regdate, regcount from board order by num DESC limit ?, ?";
		getConnect();
		ArrayList<BoardVO> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, (pageNumber - 1) * pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				Timestamp regdate = rs.getTimestamp("regdate");
				int regcount = rs.getInt("regcount");
				
				BoardVO vo = new BoardVO(num, title, null, writer, regdate, regcount, null);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}
	
	// 데이터(게시글)의 전체 개수 확인
	public int getTotalRecords() {
		String SQL = "select count(*) from board";
		getConnect();
		int totalRecords = 0;
		
		try {
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				totalRecords = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return totalRecords;
	}
	
	// ===================================
	
	// 리스트
	public ArrayList<BoardVO> boardList() {
		String SQL = "select num, title, writer, regdate, regcount from board order by num DESC";
		getConnect();
		ArrayList<BoardVO> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				Timestamp regdate = rs.getTimestamp("regdate");
				int regcount = rs.getInt("regcount");
				
				BoardVO vo = new BoardVO(num, title, null, writer, regdate, regcount, null);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}
	
	// ===================================
	
	// 검색	
	public ArrayList<BoardVO> getSearch(String searchField, String searchText) {
		ArrayList<BoardVO> list = new ArrayList<>();
		
		String SQL = "select * from board where " + searchField.trim();
		getConnect();
		try {
			if(searchText != null && !searchText.equals("")) {  // AND
				SQL += " like '%" + searchText.trim() + "%' order by num DESC";
			}
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();  // select
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
	            String writer = rs.getString("writer");
	            Timestamp regdate = rs.getTimestamp("regdate");
	            int regcount = rs.getInt("regcount");

	            BoardVO vo = new BoardVO(num, title, null, writer, regdate, regcount, null);
	            list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return list;
	}
			
	// 글 등록(저장)
	public int boardInsert(BoardVO vo) {
		String SQL = "insert into board(title, content, writer, pw) values (?, ?, ?, ?)";
		getConnect();
		int cnt = -1;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setString(3, vo.getWriter());
			ps.setString(4, vo.getPw());
			
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}
	
	// 글 수정
	public int boardUpdate(BoardVO vo) {
		String SQL = "update board set title=?, content=? where num=?";
		getConnect();	
		int cnt = -1;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setInt(3, vo.getNum());
			
			cnt = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}
	
	// 글 삭제
	public int boardDelete(int num) {
		String SQL = "delete from board where num=?";
		getConnect();
		int cnt = -1;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			
			cnt = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}
	
	// 비밀번호 검사 (수정시)
	public boolean checkPw(int num, String pw) {
		String SQL = "select pw from board where num=?";
		getConnect();
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				String dbPw = rs.getString("pw");
				return pw.equals(dbPw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return false;
	}
	
	// 글 상세보기
	public BoardVO boardContent(int num) {
		String SQL = "select * from board where num=?";
		getConnect();
		BoardVO vo = null;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				num = rs.getInt("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				Timestamp regdate = rs.getTimestamp("regdate");
				int regcount = rs.getInt("regcount");
				content = content.replace("\n", "<br>");
				
				vo = new BoardVO(num, title, content, writer, regdate, regcount, null);
				
				upRegcount(num, regdate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return vo;
	}
	
	// 조회수 증가 (제한적)
	private void upRegcount(int num, Timestamp regdate) {
		String SQL = "update board set regcount = regcount + 1 where num=?";
		
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// DB 연동 해제
	public void dbClose() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}