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
	
	// 글 등록(저장)
	public int boardInsert(BoardVO vo) {
		String SQL = "insert into board(title, content, writer) values (?, ?, ?)";
		getConnect();
		int cnt = -1;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setString(3, vo.getWriter());
			
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}
	
	// 리스트 조회
	public ArrayList<BoardVO> boardList() {
		String SQL = "select num, title, writer, regdate, regcount from board order by num DESC";
		getConnect();
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				Timestamp regdate = rs.getTimestamp("regdate");
				int regcount = rs.getInt("regcount");
				
				BoardVO vo = new BoardVO(num, title, null, writer, regdate, regcount);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}
	
	// 리스트 수정
	
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
	
	// 글 보기
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
				
				vo = new BoardVO(num, title, content, writer, regdate, regcount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return vo;
	}
	
	public ArrayList<BoardVO> getSearch(String searchField, String searchText) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		String SQL = "select * from board where " + searchField.trim();
		
		try {
			if(searchText != null && !searchText.equals("")) {
				SQL += " LIKE '%" + searchText.trim()+"%' order by num desc";
			}
			PreparedStatement ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				Timestamp regdate = rs.getTimestamp("regdate");
				int regcount = rs.getInt("regcount");
				
				BoardVO vo = new BoardVO(num, title, null, writer, regdate, regcount);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return list;
	}
	
	
	// DB 연동 해제
	public void dbClose() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}