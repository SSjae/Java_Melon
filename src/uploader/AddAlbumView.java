package uploader;//임다솔

import java.util.Scanner;

import dao.AlbumDAO;
import dao.Session;
import dto.AlbumDTO;

public class AddAlbumView {
	public AddAlbumView() {
		AlbumDAO adao = new AlbumDAO();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("--------------------------------------------------------------------");
		System.out.println("'" + Session.get("login_id") + "'" + "님의 멋진 앨범을 등록해주세요!");
		System.out.println("--------------------------------------------------------------------");
		System.out.print("앨범명 입력 : ");
		String albumname = sc.nextLine();
		System.out.print("발매일(YYYY-MM-DD)입력 : ");
		String reldate = sc.nextLine();
		System.out.print("아티스트 입력 : ");
		String producer = sc.nextLine();
		
		AlbumDTO album = new AlbumDTO(albumname, reldate, producer, Session.get("login_id"));
  
		if(adao.addAlbum(album)) {
			System.out.println("------------------------------------------");
			System.out.println("["+albumname+"]" + "앨범을 추가했습니다!");
	     }
		else {
			System.out.println("알 수 없는 오류 / 다음에 다시 시도해 주세요.");
		}
	}
}