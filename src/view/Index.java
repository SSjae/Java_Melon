package view;

import java.util.Scanner;

import dao.MelonThread;
import dao.UserDAO;

public class Index {

	public static void main(String[] args) {
		System.out.println("일조 자바 프로젝트 / 멜론 시스템" );
		
		Scanner sc =  new Scanner(System.in);
		UserDAO udao = new UserDAO();
		MelonThread mth = new MelonThread("♪");
		
		Thread th = new Thread(mth);
		System.out.println("멜론 플레이어를 실행합니다.");
		System.out.print("로딩중... ");
		th.start();
		try {th.join();} catch (InterruptedException e) {}
		System.out.println("\n로딩 완료!!");
		
		while(true) {
			System.out.println("\n\n--------------------------------------------------------------------");
			System.out.println("Melon::음악이 필요한 순간, 멜론");
			System.out.println("No.1 뮤직플랫폼 멜론! 최신 트렌드부터 나를 아는 똑똑한 음악추천까지!♬♪♪");
			System.out.println("--------------------------------------------------------------------");
			System.out.println();
			
			System.out.println("멜론을 더 안전하게 이용하세요.");
			System.out.println("1. 회원가입\n2. 로그인\n3. 종료");
			System.out.println("--------------------------------------------------------------------");
			System.out.print("메뉴를 선택해주세요 : ");
			int choice = sc.nextInt();
			
			if (choice == 3 ) {
				System.out.println("멜론 플레이어를 종료합니다.");
				break;
			}
			switch (choice) {
			case 1:
				new JoinView();
				break;
				
			case 2:
				new LoginView();
				break;

			default:
				break;
			}
		}
	}
}
