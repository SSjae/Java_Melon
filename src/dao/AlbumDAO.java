package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.AlbumDTO;

public class AlbumDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public AlbumDAO() {
		conn = DBConnection.getConnection();
	}
		
	// 앨범 등록
	public boolean addAlbum(AlbumDTO album) {
		String sql = "insert into album (albname,reldate,producer,userid)"
				+ " values(?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, album.albname);
			ps.setString(2, album.reldate);
			ps.setString(3, album.producer);
			ps.setString(4, album.userid);
			
			return ps.executeUpdate() == 1;
		}  catch (SQLException sqle) {
			System.out.println("쿼리 수행 실패 : "+sqle);
		}
		return false;
	}
	
	// 선택된 앨범 삭제
		public boolean removeAlbum(int albnum) {
			String sql = "delete from album where albnum = ?";
					
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, albnum);
				
				return ps.executeUpdate() == 1;
			} catch (SQLException sqle) {
				System.out.println("쿼리 수행 실패 : " + sqle);
			}
			return false;
		}
	
	
	// 선택된 앨범 정보
		public String albumInfo(int albnum) {
			String sql = "select * from album where albnum = ?";
			String result = "";
	        try {
	            ps = conn.prepareStatement(sql);
	            ps.setInt(1, albnum);

	            rs = ps.executeQuery();

	            if (rs.next()) {
	            	result += "앨범 제목 : " + rs.getString("albname") + "\n";
	            	result += "프로듀서 : " + rs.getString("producer") + "\n";
	            	result += "발매 날짜 : " + rs.getString("reldate");
	            }
	        } catch (SQLException sqle) {
	            System.out.println("쿼리 수행 실패 : " + sqle);
	        }
	        return result;
		}
	
	
		// 선택된 앨범 수정
		public boolean modifyAlbum(int albnum, int choice, String newData) {
			String[] cols = {"albname", "reldate", "producer"};
			String sql = "update album set " + cols[choice-1] + " = ? where userid = ? and albnum = ?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, newData);
				ps.setString(2, Session.get("login_id"));
				ps.setInt(3, albnum);
				
				return ps.executeUpdate() == 1;
			} catch (SQLException sqle) {
				System.out.println("쿼리 수행 실패 : " + sqle);
			}
			return false;
		}
	
	// 내가 올린 앨범 보기
		public String uploaderAlbList() {
			String sql = "select * from album where userid = ?";
			String result = "";
			int i = 0;
					
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, Session.get("login_id"));
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					String albname = rs.getString("albname");
					String producer = rs.getString("producer");
					String reldate = rs.getString("reldate");
					
					result += String.format("%d\t\t%s\t\t%s\t\t%s\n", ++i, albname, producer, reldate);
				}
			} catch (SQLException sqle) {
				System.out.println("쿼리 수행 실패 : " + sqle);
			}
			return result;
		}
		
		// 내가 올린 앨범의 albnum
		public int uploaderAlbListAlbnum(int choice) {
			String sql = "select * from album where userid = ?";
			int i = 0;
			int albnum = 0;
					
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, Session.get("login_id"));
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					++i;
					if(choice == i) {
						albnum = rs.getInt("albnum");
					}
				}
			} catch (SQLException sqle) {
				System.out.println("쿼리 수행 실패 : " + sqle);
			}
			return albnum;
		}
	

	
		public String albNameSearch(String search) {
			String sql = "select * from album where albname like ?";
			String result = "";
			int i = 0;
			
			try {
				ps = conn.prepareStatement(sql);
		           ps.setString(1, "%"+ search +"%");
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					String albname = rs.getString("albname");
					String producer = rs.getString("producer");
					
					result += String.format("%d. 제목 : %s\t프로듀서 : %s\n", ++i, albname, producer);
				}
				return result;
				
			} catch (SQLException sqle) {
				System.out.println("쿼리 수행 실패 : " + sqle);
			}
			return null;
		}

		public int reviewSearchAlbnum(int mychoice, String search) {
			String sql = "select * from album where albname like ?";
		       int albnum = 0;
		       int i = 0;

		       try {
		           ps = conn.prepareStatement(sql);
		           ps.setString(1, "%"+ search +"%");
		           rs = ps.executeQuery();
		           

		           while (rs.next()) {
		        	   ++i;
		        	   if (mychoice == i ) {
		        		   albnum = rs.getInt("albnum");
					}
				}
		       } catch (SQLException sqle) {
		           System.out.println("쿼리 수행 실패 : " + sqle);
		       }
		       return albnum;
		}

		public boolean existSong(int albnum) {
			String sql = "select * from song where albnum = ?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, albnum);
				
				rs = ps.executeQuery();
				
				return rs.next();
			} catch (SQLException sqle) {
				System.out.println("쿼리 수행 실패 : " + sqle);
			}
			return false;
		}
}
	