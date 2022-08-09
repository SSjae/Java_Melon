package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayListDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public PlayListDAO() {
		conn = DBConnection.getConnection();
	}

	// 새 플레이리스트 추가
	public boolean addplaylist(int songnum) {
		String sql = "insert into playlist(userid, songnum) values(?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, Session.get("login_id"));
            ps.setInt(2, songnum);

            return ps.executeUpdate() == 1;
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return false;
	}
	
	// 이미 있는 곡 추가 하는지 검사
	public boolean checksong(int songnum) {
		String sql = "select * from playlist where songnum = ? and userid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, songnum);
			ps.setString(2, Session.get("login_id"));
			
			rs = ps.executeQuery();
			
			return !rs.next();
		} catch (SQLException sqle) {
			System.out.println("쿼리 수행 실패 : " + sqle);
		}
		return false;
	}
	
	// 플레이리스트가 있는지 없는지 확인
	public boolean boolplaylist(String userid) {
		String sql = "select * from playlist p where userid = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userid);

            rs = ps.executeQuery();

            return !rs.next();
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return false;
	}
	
	// 회원의 플레이리스트 조회
	public String playlist(String userid) {
		String sql = "select * from playlist p join song s on p.songnum = s.songnum"
				+ " where p.userid = ?";
		String result = "";
		int i = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userid);

            rs = ps.executeQuery();

            while (rs.next()) {
            	String songname = rs.getString("songname");
            	String singer = rs.getString("singer");
            	String regdate = rs.getString("regdate");
            	
            	result += String.format("%d\t%s\t%s\t%s\n", ++i, songname, singer, regdate);
            }
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return result;
	}
	
	// 고른 플레이리스트의 playlistnum 뽑기
	public int playlistnum(String userid,int choice) {
		String sql = "select * from playlist p join song s on p.songnum = s.songnum"
				+ " where p.userid = ?";
		int playlistnum = 0;
		int i = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userid);

            rs = ps.executeQuery();

            while (rs.next()) {
            	++i;
            	if(i == choice) {
            		playlistnum = rs.getInt("playlistnum");
            	}
            }
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return playlistnum;
	}
	
	// 플레이리스트로 뽑힌 노래 번호 가져오기
	public int playlistsongnum(int playlistnum) {
		String sql = "select * from playlist where playlistnum = ?";
		int songnum = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, playlistnum);

            rs = ps.executeQuery();
            
            if(rs.next()) {
            	songnum = rs.getInt("songnum");
            }
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return songnum;
	}
		
	// 플레이리스트 삭제
	public boolean delplaylist(int playlistnum) {
		String sql = "delete from playlist where playlistnum = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, playlistnum);

            return ps.executeUpdate() == 1;
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return false;
	}
}