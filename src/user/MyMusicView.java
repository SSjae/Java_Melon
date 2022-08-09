package user;

import java.util.Scanner;

import dao.PlayListDAO;
import dao.Session;

public class MyMusicView {
	public MyMusicView() {
		PlayListDAO pdao = new PlayListDAO();

		if (pdao.boolplaylist(Session.get("login_id"))) {
			System.out.println("※플레이리스트 추가 후 이용 해주세요※");
		} else {
			while(true) {
				new NowPlayingView();
				
				Scanner sc = new Scanner(System.in);
				
				System.out.println("♬♩♪나의 플레이리스트♩♪♬");
				System.out.println("번호\t제목\t가수\t플레이리스트 등록날짜\t");
				System.out.println("-------------------------------------------");
				System.out.println(pdao.playlist(Session.get("login_id")));
				System.out.println("-------------------------------------------\n");
			
				System.out.println("1. 플레이리스트 선택\n2. 플레이리스트 삭제\n3. 뒤로 가기");
				System.out.println("-------------------------------------------");
				System.out.print("메뉴 선택 : ");
				int choice = sc.nextInt();
				System.out.println("-------------------------------------------");
				
				if(choice == 3) {
					System.out.println("뒤로 갑니다.");
					break;
				}
				switch (choice) {
				case 1:
					System.out.print("플레이리스트 번호 선택 : ");
					int choice1 = sc.nextInt();
					new SelMusicView(pdao.playlistsongnum(pdao.playlistnum(Session.get("login_id"), choice1)));
					break;
				case 2:
					System.out.print("삭제할 플레이리스트 번호 선택 : ");
					int delplaylistnum = sc.nextInt();
					if(pdao.delplaylist(pdao.playlistnum(Session.get("login_id"), delplaylistnum))) {
						System.out.println("\n정상적으로 삭제 되었습니다.");
					} else {
						System.out.println("\n다시 시도 해주세요.");
					}
					break;
				default:
					System.out.println("다시 입력 해주세요.");
					break;
				}
			}
		}
	}
}