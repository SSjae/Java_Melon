package user;

import java.util.Scanner;

import dao.Session;
import dao.UserDAO;

public class DeleteUserView {
	public DeleteUserView() {
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			UserDAO udao = new UserDAO();
			
			System.out.print("비밀번호를 재확인합니다 : ");
			
			String userpw = sc.next();
			if (udao.checkPw(userpw)) {
				udao.delAllReview(Session.get("login_id"));
				udao.delAllPlaylist(Session.get("login_id"));
				udao.deleteUser();
				System.out.println("\n멜론을 탈퇴합니다. 재방문을 기다리겠습니다\n감사합니다.");
				break;
			} else {
				System.out.println("\n입력하신 비밀번호가 불일치합니다.");
			}
		}
	}
}