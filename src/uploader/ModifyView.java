package uploader;

import java.util.Scanner;

import dao.UserDAO;
import user.DeleteUserView;
import user.ModifyUserView;
import user.NowPlayingView;

public class ModifyView {
	public ModifyView() {
		Scanner sc =  new Scanner(System.in);
		
		while(true) {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("마이페이지 입니다");
			System.out.println("1. 회원 정보 수정 및 추가\n2. 회원 탈퇴\n3. 마이 페이지 나가기");
			System.out.print("메뉴 선택 : ");
			int choice = sc.nextInt();
			System.out.println("--------------------------------------------------------------------");
			
			if (choice ==  1) {
				new ModifyUploaderView();
			} else if (choice ==  2) {
				new DeleteUploaderView();
				break;
			} else if (choice ==  3) {
				System.out.println("마이 페이지를 나갑니다");
				break;
			}
		}
	}
}