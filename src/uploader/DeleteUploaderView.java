package uploader;//임다솔

import java.util.Scanner;

import dao.Session;
import dao.UserDAO;

public class DeleteUploaderView {
	public DeleteUploaderView() {
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			UserDAO udao = new UserDAO();
			
			System.out.print("비밀번호를 재입력하세요 : ");
			String userpw = sc.next();
			if (udao.checkPw(userpw)) {
				udao.delAllSong(Session.get("login_id"));
				udao.delAllAlbum(Session.get("login_id"));
				udao.deleteUser();
				System.out.println("회원을 탈퇴합니다.");
				break;
			} else {
				System.out.println("비밀번호 오류입니다! 다시 시도해주세요");
			}
		}
	}
}