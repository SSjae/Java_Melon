package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import dto.UserDTO;

public class UserDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public UserDAO() {
		conn = DBConnection.getConnection();
	}

	// 중복된 아이디 있는지 검사
	public boolean checkDup(String userid) {
		String sql = "select * from alluser where userid = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			
			rs= ps.executeQuery();
			
			return !rs.next();
		} catch (SQLException sqle) {
			System.out.println("쿼리 수행 실패 : " + sqle);
		}
		return false;
		
	}

	// 회원가입
	public boolean join(UserDTO user) {
		String sql = "insert into alluser(userid, userpw, username, usernick, perm) values (?, ?, ?, ?, ?)";
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.userid);
			ps.setString(2, user.userpw);
			ps.setString(3, user.username);
			ps.setString(4, user.usernick);
			ps.setBoolean(5, user.perm);
			
			return ps.executeUpdate() == 1;
		} catch (SQLException sqle) {
			System.out.println("쿼리문 수행 실패 : " + sqle);
		}
		return false;
		
	}

	// 로그인
	public boolean login(String userid, String userpw) {
		String sql = "select * from alluser where userid = ? and userpw = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setString(2, userpw); 
				
			rs= ps.executeQuery();
				
			if (rs.next()) {
				Session.put("login_id", userid);
				Session.put(userpw, sql);
				return true;
			}
		} catch (SQLException sqle) {
			System.out.println("쿼리 수행 실패 : " + sqle);
		}
		return false;
	}

	// 회원이 업로더인지 일반유저인지 반환
	public boolean perm(String userid) {
        String sql = "select * from alluser where userid = ?";
        boolean perm = false;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userid);

            rs = ps.executeQuery();

            if (rs.next()) {
                perm = rs.getBoolean("perm");
            }
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return perm; // true / false
    }
	
	// 유저 정보 수정
	public boolean modifyUser(int col, String newData) {
		
		String[] cols = {"userpw", "username", "userage", "userphone", 
				"useremail", "usernick"};
		String sql = "update alluser set " + cols[col-1] + "=? where userid=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newData);
			ps.setString(2, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
		} catch (SQLException sqle) {
			System.out.println("쿼리 수행 실패 : " + sqle);
		}
		
				
		return false;
	}

	// 비밀번호 확인
	public boolean checkPw(String userpw) {
		String sql = "select * from alluser where userid = ? and userpw = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Session.get("login_id"));
			ps.setString(2, userpw);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			
		} catch (SQLException sqle) {
			System.out.println("쿼리 수행 실패 : " + sqle);
		}
		return false;
		
	}
	
	// 수정하는 건지 추가하는 건지 확인
    public boolean checkAdd(int col) {
        String[] cols = {"userpw", "username", "userage", "userphone", 
                "useremail", "usernick"};
        String sql = "select " + cols[col-1] + " from alluser where userid = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, Session.get("login_id"));

            rs = ps.executeQuery();

            if (rs.next()) {
                if(rs.getString(cols[col-1]) != null) {
                    return true;
                }
            }

        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return false;
    }
    
 // user 탈퇴시 등록한 앨범 삭제
 	public void delAllAlbum(String userid) {
 		String sql = "delete from album where userid = ?";
 		
 		try {
 			ps = conn.prepareStatement(sql);
 			ps.setString(1, userid);
 			
 			ps.executeUpdate();
 			
 		} catch (SQLException sqle) {
 			System.out.println("쿼리문 수행 실패 : " + sqle);
 		}
 	}
    
	// user 탈퇴
	public void deleteUser() {
		String sql = "delete from alluser where userid = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Session.get("login_id"));
			
			ps.executeUpdate();
			Session.put("login_id", null);
		} catch (SQLException sqle) {
			System.out.println("쿼리문 수행 실패 : " + sqle);
		}
		
	}
	
	
	// user 탈퇴시 등록한 노래 삭제
		public void delAllSong(String userid) {
			String sql = "delete from song where userid = ?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, userid);
				
				ps.executeUpdate();
				
			} catch (SQLException sqle) {
				System.out.println("쿼리문 수행 실패 : " + sqle);
			}
		}
		
		// user 탈퇴시 등록한 플레이리스트 삭제
		public void delAllPlaylist(String userid) {
			String sql = "delete from playlist where userid = ?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, userid);
				
				ps.executeUpdate();
				
			} catch (SQLException sqle) {
				System.out.println("쿼리문 수행 실패 : " + sqle);
			}
		}
		
		public void delAllReview(String userid) {
			String sql = "delete from review where userid = ?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, userid);
				
				ps.executeUpdate();
				
			} catch (SQLException sqle) {
				System.out.println("쿼리문 수행 실패 : " + sqle);
			}
		}
	
}
