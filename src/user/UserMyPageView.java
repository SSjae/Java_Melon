package user;

import java.util.Scanner;

import dao.Session;
import dao.UserDAO;

public class UserMyPageView {
	
	public UserMyPageView() {
		Scanner sc =  new Scanner(System.in);
		UserDAO udao = new UserDAO();
		
		 System.out.println("--------------------------------------------------------------------");
		 System.out.println("마이 페이지로 넘어갑니다.");
		 System.out.println("잠시만 기다려주세요.");
		 System.out.println("--------------------------------------------------------------------");
		while(true) {
			new NowPlayingView();
						
			System.out.println("<마이 페이지>");
			System.out.println("어세오세요." + Session.get("login_id") + "님");
			System.out.println("무엇을 도와드릴까요?");
			System.out.println("--------------------------------------------------------------------");
			System.out.println("[마이페이지 목록]");
			System.out.println("--------------------------------------------------------------------");
			System.out.println("1. 회원 정보 수정 및 추가\n2. 회원 탈퇴\n3. 나가기");
			System.out.println("--------------------------------------------------------------------");
			System.out.print("메뉴 선택 : ");
			int choice = sc.nextInt();
			
			if (choice ==  1) {
				System.out.println("--------------------------------------------------------------------");
		        System.out.println("회원 정보 수정 및 추가로 넘어갑니다.");
		        System.out.println("잠시만 기다려주세요.");
		        System.out.println("--------------------------------------------------------------------");
				new ModifyUserView();
			} else if (choice ==  2) {
				System.out.println("--------------------------------------------------------------------");
		        System.out.println("회원 탈퇴로 넘어갑니다.");
		        System.out.println("잠시만 기다려주세요.");
		        System.out.println("--------------------------------------------------------------------");
				new DeleteUserView();
				break;
			} else if (choice ==  3) {
				System.out.println("마이 페이지를 나갑니다. 다음에 또 이용 해주세요.");
				break;
			}
		}
	
	}
}
