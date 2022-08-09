package uploader;

import java.util.Scanner;

import dao.Session;
import dao.SongDAO;
import dao.UserDAO;

public class SongModifyView {
	public SongModifyView() {

		UserDAO udao = new UserDAO();
		SongDAO sdao = new SongDAO();
		Scanner sc = new Scanner(System.in);
		
	
		if(sdao.boolSong(Session.get("login_id"))) {
			System.out.println("※올린 노래가 없습니다, 다시 확인해 주세요※\n");
		} else {
			while(true) {
				System.out.println("--------------------------------------------------------------------");
				System.out.println("노래 수정 페이지입니다");
				System.out.println("\t\t노래 목록");
				System.out.println("--------------------------------------------------------------------");
				System.out.println("번호\t제목\t가수\t작곡가\t장르\t앨범이름");
				System.out.println("--------------------------------------------------------------------");
				System.out.println(sdao.uplodersonglist());
				System.out.println("--------------------------------------------------------------------");
				System.out.println("\n1. 수정\n2. 삭제\n3. 나가기 ");
				System.out.print("메뉴 선택 : ");
				int choice = sc.nextInt();
				
			
				if(choice == 3) {
					System.out.println("이전페이지로 이동합니다");
					break;
					
		
				}else if(choice == 2) {
					System.out.print("비밀번호를 다시 입력해 주세요 : ");
					String userpw = sc.next();
					if(udao.checkPw(userpw)) {
						System.out.print("삭제할 노래 번호를 입력해 주세요 : ");
						int choice1 = sc.nextInt();
						sdao.removeSong(sdao.uplodersongnum(choice1));
						System.out.println("삭제되었습니다\n");
						break;
					}
				
					else {
						System.out.println("※비밀번호 오류입니다※");
						break;
					}
					
			
				} else if(choice == 1) {
					System.out.print("수정할 노래 번호를 입력해 주세요 : ");
					int choice3 = sc.nextInt();
					System.out.println(sdao.songinfo(sdao.uplodersongnum(choice3)));
					System.out.println("-----------------------------------------------");
					System.out.println("\n1. 이름\n2. 정보\n3. 가사\n4. 가수\n5. 장르\n6. 작곡가\n7. 작사가 ");
					System.out.print("메뉴 선택 : ");
					int choice4 = sc.nextInt();
					System.out.println("-----------------------------------------------");
					sc = new Scanner(System.in);
					System.out.print("수정하실 정보를 입력해 주세요 : ");
					String newData = sc.nextLine();
			
					//세가지 입력받은 데이터를 DAO에 넘겨주기(행, 열, 새로운 데이터)
					if(sdao.modifySong(sdao.uplodersongnum(choice3) ,choice4, newData)){
						System.out.println("정상적으로 수정되었습니다");
						break;
					}
					else {
						System.out.println("※알 수 없는 오류. 다음에 다시 시도해 주세요※");
						break;
					}
				}
			}
		}
	}
}