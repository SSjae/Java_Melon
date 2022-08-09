package dto;

public class UserDTO {
	public String userid;
	public String userpw;
	public String username;
	public String usernick;
	public boolean perm;
	
	public UserDTO(String userid, String userpw, String username, String usernick, boolean perm) {
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.usernick = usernick;
		this.perm = perm;
	}
	
	

}
