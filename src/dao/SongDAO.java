package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.SongDTO;

public class SongDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SongDAO() {
		conn = DBConnection.getConnection();
	}
	
	// 선택된 노래 모든정보
	public String songinfo(int songnum) {
		String sql = "select * from song s join album a on s.albnum = a.albnum where songnum = ?";
		String result = "";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, songnum);

            rs = ps.executeQuery();

            if (rs.next()) {
            	String songinfo = rs.getString("songinfo");
            	String lyrics = rs.getString("lyrics");
            	
            	if(rs.getString("songinfo") == null) { songinfo = "정보가 없습니다."; }
            	if(rs.getString("lyrics") == null) { lyrics = "정보가 없습니다."; }
            	
            	result += "노래 제목 : " + rs.getString("songname") + "\n";
            	result += "가수/작곡/작사 : " + rs.getString("singer")+"/"+rs.getString("writer")+"/"+rs.getString("lyricist") + "\n";
            	result += "노래 정보 : " + songinfo + "\n";
            	result += "노래 가사 : " + lyrics + "\n";
            	result += "장르 : " + rs.getString("genre") + "\n";
            	result += "앨범 이름 : " + rs.getString("albname") + "\n";
            	result += "재생 횟수 : " + rs.getInt("playcnt") + "\n";
            	result += "좋아요 : " + rs.getInt("likecnt");
            }
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return result;
	}
	
	// 선택된 노래
	public String selmusic(int songnum) {
		String sql = "select * from song where songnum = ?";
		String result = "";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, songnum);

            rs = ps.executeQuery();

            if (rs.next()) {
            	String songname = rs.getString("songname");
            	String singer = rs.getString("singer");
            	
            	result += String.format("%s - %s ", songname, singer);
            }
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return result;
	}

	// 선택된 노래 재생횟수 업
	public boolean upplaycnt(int songnum) {
		String sql = "update song set playcnt = playcnt + 1 where songnum = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, songnum);
            
            return ps.executeUpdate() == 1;
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return false;
	}

	// 선택된 노래 좋아요 업
	public boolean uplikecnt(int songnum) {
		String sql = "update song set likecnt = likecnt + 1 where songnum = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, songnum);
            
            return ps.executeUpdate() == 1;
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return false;
	}

	// 차트 100 목록 조회
	public String chart100() {
		String sql = "select * from song order by likecnt desc";
		String result = "";
		int i = 0;
        try {
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
            	String songname = rs.getString("songname");
            	String singer = rs.getString("singer");
                String writer = rs.getString("writer");
            	int likecnt = rs.getInt("likecnt");
            	
            	if (songname.length() > 7) {
            		result += String.format("\t%d\t|\t%.7s...\t\t|\t%.7s\t|%13.8s\t|\t%d\n", 
            				++i, songname, singer, writer, likecnt);
				} else {
            	result += String.format("\t%d\t|\t\t%.7s\t\t|\t%s\t|\t%.7s\t|\t%d\n", 
            			++i, songname, singer, writer, likecnt);
				}
            }
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return result;
	}

	// 차트 100 입력 숫자에서 songnum 뽑아오기
	public int chart100songnum(int choice) {
		String sql = "select * from song order by likecnt desc";
		int i = 0;
		int songnum = 0;
        try {
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
            	++i;
            	if(choice == i) {
            		songnum = rs.getInt("songnum");
            	}
            }
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return songnum;
	}
	
	// 모든 노래 조회
	public String allsong() {
      String sql = "select * from song";
      String result = "";
      int i = 0;
        try {
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
               String songname = rs.getString("songname");
               String singer = rs.getString("singer");
               String writer = rs.getString("writer");
               String genre = rs.getString("genre");

               
//               result += String.format("%d\t%s\t%s\t%s\t%s\n", ++i, songname, singer, writer ,genre);
               if (songname.length() > 7) {
           			result += String.format("\t%d\t|\t%.7s...\t\t|\t%.7s\t|%13.8s\t|\t%s\n", 
           				++i, songname, singer, writer, genre);
				} else {
					result += String.format("\t%d\t|\t\t%.7s\t\t|\t%s\t|\t%.7s\t|\t%s\n", 
           			++i, songname, singer, writer, genre);
				}
               
            }
        } catch (SQLException sqle) {
            System.out.println("쿼리 수행 실패 : " + sqle);
        }
        return result;
	}
	
	// 모든 노래 선택한 songnum 반환
   public int allsongsongnum(int choice) {
      String sql = "select * from song";
      int i = 0;
      int songnum = 0;
      try {
         ps = conn.prepareStatement(sql);
         
         rs = ps.executeQuery();
         
         while (rs.next()) {
            ++i;
            if(choice == i) {
               songnum = rs.getInt("songnum");
            }
         }
      } catch (SQLException sqle) {
         System.out.println("쿼리 수행 실패 : " + sqle);
      }
      return songnum;
   }
   
   // 검색 목록 조회
   public String search(String keyword, int choice) {
       String [] cols = {"songname", "genre", "singer"};
       String sql = "select * from song where "+cols[choice-1]+" like ?";
       String result = "";
       int i = 0;

       try {
           ps = conn.prepareStatement(sql);
           ps.setString(1, "%"+keyword+"%");
           rs = ps.executeQuery();

           while(rs.next()) {
        	   String songname = rs.getString("songname");
               String singer = rs.getString("singer");
               String writer = rs.getString("writer");
               String genre = rs.getString("genre");

//               result += String.format("%d\t%s\t%s\t%s\t%s\n", ++i, songname, singer, writer ,genre);
               if (songname.length() > 7) {
          			result += String.format("\t%d\t|\t%.7s...\t\t|\t%.7s\t|%13.8s\t|\t%s\n", 
          				++i, songname, singer, writer, genre);
				} else {
					result += String.format("\t%d\t|\t\t%.7s\t\t|\t%s\t|\t%.7s\t|\t%s\n", 
          			++i, songname, singer, writer, genre);
				}
           }
       } catch (SQLException sqle) {
           System.out.println("쿼리 수행 실패 : " + sqle);
       }
       return result;
   }

   // 검색 목록 조회 songnum 가져오기
   public int searchsongnum(String keyword, int choice, int songchoice) {
       String [] cols = {"songname", "genre", "singer"};
       String sql = "select * from song where "+cols[choice-1]+" like ?";
       int i = 0;
       int songnum = 0;

       try {
           ps = conn.prepareStatement(sql);
           ps.setString(1, "%"+keyword+"%");
           rs = ps.executeQuery();

           while(rs.next()) {
               ++i;
               if(songchoice == i) {
                   songnum = rs.getInt("songnum");
               }
           }
       } catch (SQLException sqle) {
           System.out.println("쿼리 수행 실패 : " + sqle);
       }
       return songnum;
   }

   // 업로더 올린 모든 노래 리스트
   public String uplodersonglist() {
       String sql = "select * from song where userid = ?";
       String result = "";
       int i = 0;

       try {
           ps = conn.prepareStatement(sql);
           ps.setString(1, Session.get("login_id"));
           rs = ps.executeQuery();

           while(rs.next()) {
               String songname = rs.getString("songname");
               String singer = rs.getString("singer");
               String writer = rs.getString("writer");
               String genre = rs.getString("genre");

               result += String.format("%d\t\t%s\t\t%s\t\t%s\t\t%s\n", ++i, songname, singer, writer ,genre);
           }
       } catch (SQLException sqle) {
           System.out.println("쿼리 수행 실패 : " + sqle);
       }
       return result;
   }

   // 업로더 선택된 노래 리스트 songnum
   public int uplodersongnum(int choice) {
       String sql = "select * from song where userid = ?";
       int i = 0;
       int songnum = 0;

       try {
           ps = conn.prepareStatement(sql);
           ps.setString(1, Session.get("login_id"));
           rs = ps.executeQuery();

           while(rs.next()) {
               ++i;
               if(choice == i) {
                   songnum = rs.getInt("songnum");
               }
           }
       } catch (SQLException sqle) {
           System.out.println("쿼리 수행 실패 : " + sqle);
       }
       return songnum;
   }
   
   // 선택된 노래 아예 삭제
   public void removeSong(int songnum) {
	   String sql = "delete from song where userid = ? and songnum = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Session.get("login_id"));
			ps.setInt(2, songnum);
			
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("쿼리문 수행 실패 : " + sqle);
		}
	}
   
   // 올린 노래 수정
   public boolean modifySong(int songnum, int choice, String newData) {
		String[] cols = {"songname", "songinfo", "lyrics", "singer", "genre", "writer", "lyricist"};
		String sql = "update song set " + cols[choice-1] + " = ? where userid = ? and songnum = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newData);
			ps.setString(2, Session.get("login_id"));
			ps.setInt(3, songnum);
			
			return ps.executeUpdate() == 1;
		} catch (SQLException sqle) {
			System.out.println("쿼리 수행 실패 : " + sqle);
		}
		return false;
	}
   
   // 올린 노래가 있는지 없는지 검사
   public boolean boolSong(String userid) {
		String sql = "select * from song where userid = ?";
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

   
   // 노래 추가
   public boolean addSong(SongDTO song) {
	   String sql = "insert into song(songname, songinfo, lyrics, singer, genre, writer, lyricist, albnum, userid)"
	   		+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, song.songname);
			ps.setString(2, song.songinfo);
			ps.setString(3, song.lyrics);
			ps.setString(4, song.singer);
			ps.setString(5, song.genre);
			ps.setString(6, song.writer);
			ps.setString(7, song.lyricist);
			ps.setInt(8, song.albnum);
			ps.setString(9, song.userid);
			
			return ps.executeUpdate() == 1;
		} catch (SQLException sqle) {
			System.out.println("쿼리문 수행 실패 : " + sqle);
		}
		return false;
   }

   
   public String songNameSearch(String search) {
       String sql = "select * from song where songname like ?";
	   String result = "";
	   int i = 0;
	   
	   try {
		ps = conn.prepareStatement(sql);
        ps.setString(1, "%"+search+"%");
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			String songname = rs.getString("songname");
			String singer = rs.getString("singer");
			
			result += String.format("%d. 제목 : %s\t가수 : %s\n", ++i, songname, singer);
		}
	   } catch (SQLException sqle) {
		System.out.println("쿼리문 수행 실패 : " + sqle);
	   }
	   return result;	//모든 정보가 저장된 result 리턴
   }
   
   // 리뷰용 songnum
   public int reviewSearchSongnum(int mychoice, String keyword) {
       String sql = "select * from song where songname like ?";
       int songnum = 0;
       int i = 0;

       try {
           ps = conn.prepareStatement(sql);
           ps.setString(1, "%"+keyword+"%");
           rs = ps.executeQuery();

           while (rs.next()) {
        	   ++i;
        	   if (mychoice == i) {
        		   songnum = rs.getInt("songnum");
			}
		}
       } catch (SQLException sqle) {
           System.out.println("쿼리 수행 실패 : " + sqle);
       }
       return songnum;
   }
}