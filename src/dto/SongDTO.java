package dto;

public class SongDTO {
	public int songnum;
	public String songname;
	public String songinfo;
	public String lyrics;
	public String singer;
	public int likecnt;
	public int playcnt;
	public String genre;
	public String writer;
	public String lyricist;
	public int albnum;
	public String userid;
	
	public SongDTO(String songname, String songinfo, String lyrics, String singer, String genre,
			String writer, String lyricist, int albnum, String userid) {
		this.songname = songname;
		this.songinfo = songinfo;
		this.lyrics = lyrics;
		this.singer = singer;
		this.genre = genre;
		this.writer = writer;
		this.lyricist = lyricist;
		this.albnum = albnum;
		this.userid = userid;
	}
	
}
