package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ReviewDAO() {
		conn = DBConnection.getConnection();
	}
	
	public boolean reviewWrite(int choice, String review) {
		
		String[] cols = {"songnum", "albnum"};
		String sql =  "insert into review(reviewdetail," + cols[choice - 1]+", userid) values(?, ?, ?);";
		boolean check =  false;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, review);
			if(choice == 1) {
			ps.setInt(2, Integer.parseInt(Session.get("songnum")));
			} else {
				ps.setInt(2, Integer.parseInt(Session.get("albnum")));
			}
			ps.setString(3, Session.get("login_id"));
			
			check =  ps.executeUpdate() == 1;
		} catch (SQLException sqle) {
			System.out.println("리뷰 쿼리 수행 실패 : " + sqle);
		}
		return check;
		
	}

	public String showMyReview() {
		String sql = "select * from review r left join album a on r.albnum = a.albnum "
				+ "left join song s on r.songnum = s.songnum where r.userid = ?;";
		String result = "";
		int i = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Session.get("login_id"));
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String regdate = rs.getString("regdate");
				String reviewdetail = rs.getString("reviewdetail");
				int songnum = rs.getInt("songnum");
				int albnum = rs.getInt("albnum");
				String songname = rs.getString("songname");
				String albname = rs.getString("albname");
				
				if(songnum != 0) {
					if(songname.length() > 7) {
						if(reviewdetail.length() < 8) {
							result += String.format("%d. 노래 제목 : %.7s...\t리뷰 내용 : %s\t/t/t작성 날짜 : %s\n",
									++i, songname, reviewdetail, regdate);
						} else {
							result += String.format("%d. 노래 제목 : %.7s...\t리뷰 내용 : %s\t\t작성 날짜 : %s\n",
							++i, songname, reviewdetail, regdate);
						}
					} else {
						if(reviewdetail.length() < 8) {
							result += String.format("%d. 노래 제목 : %s\t리뷰 내용 : %s\t\t작성 날짜 : %s\n",
									++i, songname, reviewdetail, regdate);
						} else {
							result += String.format("%d. 노래 제목 : %s\t리뷰 내용 : %s\t\t작성 날짜 : %s\n",
									++i, songname, reviewdetail, regdate);
						}
					}
				} else if(albnum != 0) {
					if(albname.length() > 7) {
						if(reviewdetail.length() < 8) {
							result += String.format("%d. 앨범 제목 : %.7s...\t리뷰 내용 : %s\t\t\t작성 날짜 : %s\n",
									++i, albname, reviewdetail, regdate);
						} else {
							result += String.format("%d. 앨범 제목 : %.7s...\t리뷰 내용 : %s\t\t작성 날짜 : %s\n",
									++i, albname, reviewdetail, regdate);
						}
					} else {
						if(reviewdetail.length() < 8) {
							result += String.format("%d. 앨범 제목 : %s\t리뷰 내용 : %s\t\t작성 날짜 : %s\n",
									++i, albname, reviewdetail, regdate);
						} else {
							result += String.format("%d. 앨범 제목 : %s\t리뷰 내용 : %s\t\t작성 날짜 : %s\n",
									++i, albname, reviewdetail, regdate);
						}
					}
				}
			}
		} catch (SQLException sqle) {
			System.out.println("쿼리 수행 실패 : " + sqle);
		}
		return result;
	}
	
	
	public int getReviewNum(int mychoice) {
		String sql = "select * from review r left join album a on r.albnum = a.albnum "
				+ "left join song s on r.songnum = s.songnum where r.userid = ?;";
		int i = 0;
		int reviewnum = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Session.get("login_id"));
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int songnum = rs.getInt("songnum");
				int albnum = rs.getInt("albnum");
				
				if(songnum != 0) {
					++i;
					if (mychoice == i) {
						reviewnum = rs.getInt("reviewnum");
					}
				} else if(albnum != 0) {
					++i;
					if (mychoice == i) {
						reviewnum = rs.getInt("reviewnum");
					}
				}
			}
			
		} catch (SQLException sqle) {
			System.out.println("쿼리 수행 실패 : " + sqle);
		}
		return reviewnum;
	}
	
	
	

	public boolean modifyReview(int getreviewnum, String review) {
		String sql = "update review set reviewdetail = ? where reviewnum = ? and userid = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, review);
			ps.setInt(2, getreviewnum);
			ps.setString(3, Session.get("login_id"));
			
			return ps.executeUpdate() == 1;
			
		} catch (SQLException sqle) {
			System.out.println("쿼리 수행 실패 : " + sqle);
		}
		return false;
	}
	
	
	public boolean deleteReview(int reviewnum) {
        String sql = "delete from review where reviewnum = ? and userid = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, reviewnum);
            ps.setString(2, Session.get("login_id"));

            return ps.executeUpdate() == 1;
        } catch (SQLException sqle) {
            System.out.println("쿼리문 수행 실패 : " + sqle);
        }
        return false;
    }
	

}
