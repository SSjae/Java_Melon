package uploader;

import java.util.Scanner;

public class UploaderView {
	public UploaderView() {
		
		Scanner sc = new Scanner(System.in);
		

		while(true) {
			System.out.println("--------------------------------------------------------------------");
			System.out.print("\n1. 앨범등록\n2. 노래등록	\n3. 마이페이지\n4. 로그아웃\n");
			System.out.println("--------------------------------------------------------------------\n");
			System.out.print("메뉴 선택 : ");
			int choice = sc.nextInt();

			if(choice == 4) {
				System.out.println("로그아웃을 합니다.‍");
				break;
			}
	
			switch(choice) {
			case 1:
				new AddAlbumView();
				break;
			case 2:
				new AddSongView();
				break;
			case 3:
				new UploaderMyPageView();			
				break;
				
			
			default:
				System.out.println("‍※다시 입력해 주세요※\n");
			}
		}
	}
}