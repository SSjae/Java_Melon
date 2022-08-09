package user;

import java.util.Scanner;

import dao.Session;
import dao.SongDAO;
import review.ReviewView;

public class UserView {
	public UserView() {
		SongDAO sdao = new SongDAO();
		Scanner sc = new Scanner(System.in);
		
		Session.put("now_palying", null);
		
		while(true) {
			
			if(Session.get("login_id") == null) {
				break;
			}
			
			new NowPlayingView();
			
				System.out.println("--------------------------------------------------------------------");
	            System.out.println("No.1 뮤직플랫폼 Melon, 음악이 필요한 순간!♬♪♪");
	            System.out.println("Melon 목록");
	            System.out.println("--------------------------------------------------------------------");
				System.out.println("1. 오늘의 TOP 100\n2. 'Melon'의 모든 노래 리스트\n3. 노래 검색\n4. 나의 플레이리스트\n"
						+ "5. 마이 페이지\n6. 리뷰 페이지\n7. 로그아웃");
				System.out.println("--------------------------------------------------------------------");
				System.out.print("메뉴 선택 : ");
				int choice = sc.nextInt();
				
				if (choice == 7) {
					System.out.println(Session.get("login_id") + "님 안녕히가세요\n");
					Session.put("login_id", null);
					break;
				}
			switch (choice) {
			case 1:
				System.out.println("---------------------------------------------"
						+ "TOP 100----------------------------------------------\n");
				System.out.println("\t번호\t|\t\t제목\t\t|\t가수\t|\t작곡가\t|\t좋아요");
				System.out.println("----------------------------------------------------------"
						+ "----------------------------------------");
				System.out.println(sdao.chart100());
				System.out.print("노래 선택 : ");
				choice = sc.nextInt();

				new SelMusicView(sdao.chart100songnum(choice));
				break;
				
			case 2:
				System.out.println("----------------------------------------------------------"
						+ "----------------------------------------");
		        System.out.println("발매한 모든 음악이 있습니다.");
				System.out.println("No.1 뮤직플랫폼의 All 노래 리스트♬♪♪\n");
				System.out.println("\t번호\t|\t\t제목\t\t|\t가수\t|\t작곡가\t|\t장르\t");
				System.out.println("----------------------------------------------------------"
						+ "----------------------------------------");
	            System.out.println(sdao.allsong());
	            System.out.println("----------------------------------------------------------"
						+ "----------------------------------------");
	            System.out.print("노래 선택 : ");
	            choice = sc.nextInt();
	            
	            new SelMusicView(sdao.allsongsongnum(choice));
	            break;

			case 3:						      
				new SongSearchView();
				break;
				
			case 4:
				new MyMusicView();
				break;
				
			case 5:		       
				new UserMyPageView();
				break;
				
			case 6:		        
				new ReviewView();
				
			default:
				break;
			}
		}
	}
}