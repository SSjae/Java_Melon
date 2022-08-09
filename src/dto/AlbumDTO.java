package dto;

public class AlbumDTO {
	
	public int albnum;
	public String albname;
	public String regdate;
	public String reldate;
	public String producer;
	public String userid;
	

	public AlbumDTO(String albname, String reldate, String producer, String userid) {
		this.albname = albname;
		this.reldate = reldate;
		this.producer = producer;
		this.userid = userid;
	}
}
