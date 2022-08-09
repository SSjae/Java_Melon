package user;

import java.util.Scanner;

import dao.PlayListDAO;
import dao.Session;
import dao.SongDAO;

public class SelMusicView {
	public SelMusicView(int songnum) {
		Scanner sc = new Scanner(System.in);
		SongDAO sdao = new SongDAO();
		PlayListDAO pdao = new PlayListDAO();
		
		
		while(true) {
			new NowPlayingView();

			System.out.println(sdao.songinfo(songnum));
			System.out.println("------------------------------------------------------");
			System.out.println("1. 재생\n2. 좋아요\n3. 플레이리스트 추가\n4. 나가기");
			System.out.println("------------------------------------------------------");
			System.out.print("메뉴 선택 : ");
			int choice = sc.nextInt();
			
			if (choice == 4) {
				break;
			}
			switch (choice) {
			case 1:
				// 재생횟수 업
				if(sdao.upplaycnt(songnum)) {
					Session.put("now_playing", songnum + "");
				} else {
					System.out.println("다시 한번 시도해주세요.");
				}
				break;
				
			case 2:
				// 좋아요 업
				if(sdao.uplikecnt(songnum)) {
					System.out.println("------------------------------------------------------");
					System.out.println("좋아요! 감사합니다.");
					System.out.println("------------------------------------------------------");
				} else {
					System.out.println("다시 한번 시도해주세요.");
				}
				break;

			case 3:
				// 플레이리스트 추가
				if(pdao.checksong(songnum)) {
					pdao.addplaylist(songnum);
					System.out.println("------------------------------------------------------");
					System.out.println("플레이리스트에 추가됐습니다.");
					System.out.println("------------------------------------------------------");
				} else {
					System.out.println("이미 추가 되어있는 곡입니다. 다른 곡은 어떠신가요?");
				}
				break;
				
			default:
				break;
			}
		}
	}
}